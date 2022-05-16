package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingDequeueTest {

    @Test
    void testLIFO() throws InterruptedException {
        var queue = new LinkedBlockingDeque<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {

            final var index = i;

            try {
                queue.putLast("data-" + index);
                System.out.println("put data " + index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var data = queue.takeLast();
                    System.out.println("receive : " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testFIFO() throws InterruptedException {
        var queue = new LinkedBlockingDeque<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {

            final var index = i;

            try {
                queue.putLast("data-" + index);
                System.out.println("put data " + index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var data = queue.takeFirst();
                    System.out.println("receive : " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
