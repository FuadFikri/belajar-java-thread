package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class TransferQueueTest {

//pengirim data ke queue menunggu sampai ada yang menerima data
    @Test
    void test() throws InterruptedException {
        var queue = new LinkedTransferQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {

            final var index = i;
            executor.execute(() -> {
                try {
                    queue.transfer("data-" + index);
                    System.out.println("put data " + index);
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
