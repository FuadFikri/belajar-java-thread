package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {


    @Test
    void test() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        final var executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {

                try {
                    System.out.println("start task");
                    Thread.sleep(2000);
                    System.out.println("end task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(countDownLatch.getCount());
                    countDownLatch.countDown();
                }
            });

        }

        executorService.execute(() -> {
            try {
                countDownLatch.await();
                System.out.println("finish all task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
