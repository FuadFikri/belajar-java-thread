package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    @Test
    void create() {
        var minThread = 10;
        var maxThread = 10;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);


        var threadpool = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

    }

    @Test
    void executeRunnable() throws InterruptedException {
        var minThread = 10;
        var maxThread = 10;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);


        var threadPoolExecutor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);


        Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
                System.out.println("runnable thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        threadPoolExecutor.execute(runnable);

        Thread.sleep(6000);

    }

    @Test
    void stopThreadPool() throws InterruptedException {
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(10000);


        var threadPoolExecutor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

        for (int i = 0; i < 1000; i++) {

Thread.sleep(100);
            int finalI = i;
            Runnable runnable = () -> {
                System.out.println("task " + finalI + "runnable thread : " + Thread.currentThread().getName());

            };
            threadPoolExecutor.execute(runnable);
        }
//
//        threadPoolExecutor.shutdown();
//        threadPoolExecutor.awaitTermination(1, TimeUnit.MINUTES);
//        threadPoolExecutor.shutdownNow().forEach(runnable -> System.out.println(runnable));

    }
}
