package test;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.atomic.AtomicInteger;
import org.awaitility.Awaitility.*;

public class ReactiveStream {
    public static class EndSubscriber<T> implements Flow.Subscriber<T> {

        private AtomicInteger howMuchMessagesConsume;
        private Flow.Subscription subscription;
        public List<T> consumedElements = new LinkedList<>();

        public EndSubscriber(Integer howMuchMessagesConsume) {
            this.howMuchMessagesConsume
                    = new AtomicInteger(howMuchMessagesConsume);
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(T item) {
            howMuchMessagesConsume.decrementAndGet();
            System.out.println("Got : " + item);
            consumedElements.add(item);
            if (howMuchMessagesConsume.get() > 0) {
                System.out.println("demand");
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
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        var subscriber = new EndSubscriber<String>(1);
        publisher.subscribe(subscriber);
        var items = List.of("1", "x", "2", "x", "3", "x");
        var expected = List.of("1");
        items.forEach(publisher::submit);
    }
}
