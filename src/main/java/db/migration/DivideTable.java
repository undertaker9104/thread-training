package db.migration;

import db.gen.GenExecutor;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DivideTable {


    private Connection sourceConn() throws SQLException {
        return GenExecutor.getConnection();
    }


    // ShadingSphere
    volatile static ArrayList<Pair<String, String>> columns = null;
    volatile static String columnNames = null;


    public static void copy(String table, ResultSet rs, Connection conn) throws SQLException {

        if(columns == null || columnNames == null) {
            synchronized (DivideTable.class) {
                if(columns == null) {
                    columns = new ArrayList<>();

                    var meta = rs.getMetaData();

                    for (int i = 1; i < meta.getColumnCount(); i++) {
                        var name = meta.getColumnName(i);
                        columns.add(Pair.of(name, meta.getColumnTypeName(i)));
                    }
                    columnNames = columns.stream().map(x->x.getLeft()).collect(Collectors.joining(","));
                }
            }
        }

        var sql = String.format("insert into %s (%s) values (%s)",
                table,
                columnNames,
                columns.stream().map(x -> "?").collect(Collectors.joining(","))
        );
        try(var stmt = conn.prepareStatement(sql)){
            while(rs.next()) {
                for(int i = 0; i < columns.size(); i++) {
                    stmt.setObject(i+1, rs.getObject(i+1));
                }
                stmt.addBatch();
            }
            stmt.executeBatch();
        }catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(sql);
        }
    }


    public static void main(String[] argv) throws IOException, SQLException, InterruptedException {


        var batch = 5000;
        var batchSize = 20000;
        var threadNum = 10;
        var table = "t_post";
        var sourceTable = "huge.post";
        var latcher = new CountDownLatch(batch);
        var ds = ThreadLocal.withInitial(() -> {
            try {
                var file = new File("./data/sql/sharding.yml");
                DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(file);
                return dataSource;
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
            return null;
        });

        //https://www.bilibili.com/video/BV1Xv411b7eU
        // RxJava
        var counter = new AtomicInteger(0);
        var start = System.currentTimeMillis();
        Observable.range(0, batch)
                .window(batch / threadNum)
                .subscribe(o ->
                        o.subscribeOn(Schedulers.newThread())
                                .subscribe(batchId -> {

                                    var success = false;
                                    while (!success) {
                                        try {
                                            batchId = counter.getAndIncrement();


                                            try (var connection = ds.get().getConnection()) {
                                                try (var stmt = connection.createStatement()) {
                                                    var rs = stmt.executeQuery(String.format("select * from %s limit %d,%d", sourceTable, batchId * batchSize, batchSize));
                                                    try {
                                                        copy(table, rs, connection);
                                                    } catch (Exception ex) {
                                                        ex.printStackTrace();
                                                        System.out.println(ex.getCause());
                                                    }
                                                    rs.close();
                                                }
                                                latcher.countDown();
                                                var dt = (System.currentTimeMillis() - start) / 60000.0;
                                                System.out.format("%d finished. %d/%d, speed=%.2fW/min\n", batchId, batch - latcher.getCount(), batch, ((batch - latcher.getCount()) * batchSize) / 10000.0 / dt);
                                                success = true;
                                            }
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                    }
                                })
                );
        latcher.await();


    }
}
