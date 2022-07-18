import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.Test;
import test.ReactiveStream;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


public class ReactiveTest {
    public class EndSubscriber<T> implements Flow.Subscriber<T> {

        private AtomicInteger howMuchMessagesConsume;
        private Flow.Subscription subscription;
        public List<T> consumedElements = new LinkedList<>();

        public EndSubscriber(Integer howMuchMessagesConsume) {
            this.howMuchMessagesConsume
                    = new AtomicInteger(howMuchMessagesConsume);
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("on sub");
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(T item) {
            howMuchMessagesConsume.decrementAndGet();
            System.out.println("Got : " + item);
            consumedElements.add(item);
            if (howMuchMessagesConsume.get() > 0) {
                subscription.request(1);
            }
        }

        @Override
        public void onError(Throwable throwable) {
        }

        @Override
        public void onComplete() {
        }

    }
    @Test
    public void test(){
        var publisher = new SubmissionPublisher<String>();

        var subscriber = new EndSubscriber<String>(3);
        publisher.subscribe(subscriber);
        var items = List.of("1", "x", "2", "x", "3", "x");
        items.forEach(publisher::submit);
        await().atMost(1000, TimeUnit.MILLISECONDS).until(() -> {
            System.out.println(subscriber.consumedElements);
            return true;
        });

    }

    @Test
    public void some(){

        var r = new Random();
        var maxValue = IntStream.range(0, 10000)
                .map(i -> r.nextInt(1000))
                .boxed()
                .collect(Collectors.toList())
                .parallelStream()
                .max((a,b) -> {
                    System.out.println(Thread.currentThread().getName());
                    return a - b;
                }).get();



    }
}
