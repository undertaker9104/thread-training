package test;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class Interrupt {

    @Test
    public void test() throws InterruptedException {

        var t = new Thread(() -> {
            while(true) {
                System.out.println("here--");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // 执行这里然后继续while
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(100);
        t.interrupt();
        t.join();

    }

    @Test
    public void test1() throws InterruptedException {

        var lock = new ReentrantLock();
        var t = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                while(true) { lock.lockInterruptibly(); }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        Thread.sleep(100);
        t.interrupt();
        t.join();
    }
}
