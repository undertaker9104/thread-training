package concurrent;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockFreeStack<T> {

    static class Node<T> {

        Node<T> next;
        T value;

        public Node(T value){
            this.value = value;
            this.next = null;
        }
    }


    AtomicStampedReference<Node<T>> head;
    public LockFreeStack(){
        var headNode = new Node<T>(null);
        head = new AtomicStampedReference<>(headNode, 0);
    }

    public void push(T v){
        var newNode = new Node<T>(v);
        while(true) {
            int stamp = head.getStamp();
            Node<T> ref = head.getReference();
            newNode.next = ref;
            if(head.compareAndSet(ref, newNode, stamp, stamp+1)) {
                return;
            }
        }

    }

    public T pop(){
        while (true) {
            int stamp = head.getStamp();
            Node<T> ref = head.getReference();
            if(ref.next == null) {
                return null;
            }
            var next = ref.next;
//            head.compareAndSet(ref, next, stamp, stamp+1);
//            return ref.value;
            if( head.compareAndSet(ref, next, stamp, stamp+1) ) {
                return ref.value;
            }
        }
    }




    @Test
    public void testSingle(){

        var stack = new LockFreeStack<Integer>();

        for(int i = 0; i < 100; i++) {
            stack.push(i);
        }

        Integer j = null;
        Integer i = 99;
        while((j = stack.pop()) != null) {
            assertEquals(j+"", i-- +"");
        }
    }

    @Test
    public void testMultiThreads() throws InterruptedException {
        var stack = new LockFreeStack<Integer>();


        var list = IntStream.range(0, 16)
                .mapToObj(i -> {
                    var t = new Thread(() -> {
                        System.out.println(Thread.currentThread().getId());
                        for (int j = 0; j < 100; j++) {
                            try {
                                stack.push(j);
                                Thread.sleep(1);
                                stack.push(j);
                                Thread.sleep(1);
                                stack.pop();
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    t.start();
                    return t;
                }).collect(Collectors.toList());
            list.forEach(t -> {
                    System.out.println("wait join..");
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        Integer c = 0;
        while(stack.pop() != null) {
            c ++;
        }


        assertEquals(c+"", "1600");


    }

}
