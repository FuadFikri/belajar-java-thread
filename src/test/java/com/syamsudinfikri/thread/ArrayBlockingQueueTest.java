package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueTest {


//    queue memiliki kapasitas 5, tetapi mau execute 10 data
//      data 1 samapi 5 bisa masuk
//    data 6 - 10 tidak masuk karena penuh.
//    data 6 bisa masuk setelah di ada yang melakukan queue.take()
//    setelah ada yang melakukan queue.take(), maka data selanjutnya bisa masuk ke queue
//    begitu seterusnya
    @Test
    void test() throws InterruptedException {
        var queue = new ArrayBlockingQueue<String>(5);
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    queue.put("data");
                    System.out.println("finish put data" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    Thread.sleep(2000);
                    String data = queue.take();
                    System.out.println("receive : " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
