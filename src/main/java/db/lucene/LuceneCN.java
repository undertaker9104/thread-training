package db.lucene;

import db.gen.BookLoader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;


public class LuceneCN {

    static final String INDEX_DIR="/data1/index_data";

    private IndexWriter createWriter() throws IOException {
        var path = Paths.get(INDEX_DIR);
        var dir = FSDirectory.open(path);
        var analyzer = new IKAnalyzer();
        var config = new IndexWriterConfig(analyzer);
        return new IndexWriter(dir, config);
    }

    @Test
    public void testCreateIndex() throws IOException {
        var writer = this.createWriter();
        var doc = new Document();
        doc.add(new TextField("content", "Today I learn Lucene.", Field.Store.YES));

        writer.addDocument(doc);
        writer.flush();
        writer.close();

    }

    @Test
    public void testCreateIndexFromNovel() throws IOException, ClassNotFoundException, InterruptedException {
        var writer = createWriter();
        var books = new BookLoader();

        var docs = new LinkedBlockingQueue<Document>();
        var sentences = books.sentences();
        var latcher = new CountDownLatch(sentences.size());

        Observable.from(sentences)
                .window(2000)
                .map(o -> o.subscribeOn(Schedulers.newThread()))
                .forEach(o -> {
                    o.subscribe(sentence -> {
                        var doc = new Document();
                        doc.add(new TextField("text", sentence.getText(), Field.Store.YES));
                        doc.add(new TextField("chapter", sentence.getChapterId() + "", Field.Store.YES));
                        docs.offer(doc);
                        latcher.countDown();
                    });
                });

        latcher.await();

        while(docs.size() > 0) {
            writer.addDocument(docs.poll());
        }
        writer.flush();
        writer.close();

    }

    private IndexSearcher createSearcher() throws IOException {
        var path = Paths.get(INDEX_DIR);
        var dir = FSDirectory.open(path);
        var reader = DirectoryReader.open(dir);
        return new IndexSearcher(reader);
    }

    @Test
    public void query() throws ParseException, IOException {
        var query = new QueryParser("content", new StandardAnalyzer())
                .parse("lucene");

        var searcher = createSearcher();
        var docs = searcher.search(query, 10);

        for(var scoreDoc : docs.scoreDocs) {
            var docID = scoreDoc.doc;
            var doc = searcher.doc(docID);
            System.out.format("%s %s\n", scoreDoc.score, doc.get("content"));
        }


    }

    @Test
    public void query2() throws ParseException, IOException {
        var query = new QueryParser("text", new IKAnalyzer())
                .parse("宝玉很开心");

        System.out.println(query.getClass());
        System.out.println(query);
        var searcher = createSearcher();
        var docs = searcher.search(query, 10);

        for(var scoreDoc : docs.scoreDocs) {
            var docID = scoreDoc.doc;
            var doc = searcher.doc(docID);
            System.out.format("%s %s %s\n", scoreDoc.score, doc.get("chapter"), doc.get("text"));
        }


    }


}
