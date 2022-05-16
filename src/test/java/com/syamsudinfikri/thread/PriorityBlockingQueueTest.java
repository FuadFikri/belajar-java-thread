package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueTest {


// receive data menggunakan priority berdasarkan comparator
    @Test
    void test() throws InterruptedException {
        var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                queue.put( finalI);
                System.out.println("finish put data" + finalI);
            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    Integer data = queue.take();
                    System.out.println("receive : " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
