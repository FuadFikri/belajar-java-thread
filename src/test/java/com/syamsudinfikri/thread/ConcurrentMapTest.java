package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapTest {




    @Test
    void concurrentMap() throws InterruptedException {

        final var countDown = new CountDownLatch(100);
        final var map = new ConcurrentHashMap<Integer, String>();
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    map.putIfAbsent(index, "Data-" + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDown.countDown();
                }
            });
        }

        executor.execute(() -> {
            try {
                countDown.await();
                map.forEach((integer, s) -> System.out.println(integer + " : " + s));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

}
