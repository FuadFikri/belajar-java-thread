package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class SynchronousQueueTest {


//    ketika tidak ada yang ambil data, maka thread yang akan put data harus menunggu dulu
//    ketika ada yang minta data, maka baru dimasukan
    @Test
    void test() throws InterruptedException {
        var queue = new SynchronousQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {

            final var index = i;
            executor.execute(() -> {
                try {
                    queue.put("data-" +index);
                    System.out.println("put data "+ index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var data = queue.take();
                    System.out.println("receive : " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
