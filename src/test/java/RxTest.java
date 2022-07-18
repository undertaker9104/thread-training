import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


public class RxTest {

    CountDownLatch latch = new CountDownLatch(1);
    class RxSubscriber<T> extends Subscriber<T>{

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable throwable) {
        }

        @Override
        public void onNext(T t) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId());
//                e.printStackTrace();
            }
        }
    }

    @Test
    public void rx() throws InterruptedException {

        var pool = Executors.newFixedThreadPool(10);
        var subscriber = new RxSubscriber<Integer>();
        Observable.range(1, 100)
                .window(10)
                .map(i -> i.subscribeOn(Schedulers.from(pool)))
                .forEach(o -> {
                    o.subscribe(subscriber);
                });

        latch.await();
        System.out.println("here--");

    }

    void request(int url)  {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(url);

    }
    @Test
    public void rx2() throws InterruptedException {
        var urls = List.of(1,2,3, 4, 5, 6);
        Observable.from(urls)
                .window(100)
                .flatMap(url ->
                    Observable
                            .just(url)
                            .subscribeOn(Schedulers.newThread())
                )
                .forEach(
                        observable -> observable.subscribe(url -> request(url)
                        ));


        Thread.sleep(1000);
    }

    protected static Observable<Integer> performLongOperation(Integer v) {
            return Observable.just(v * v);
    }
}
