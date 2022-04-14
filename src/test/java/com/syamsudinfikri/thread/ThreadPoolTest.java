package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    @Test
    void  create() {
        var minThread = 10;
        var maxThread = 10;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);


        var threadpool = new ThreadPoolExecutor(minThread,maxThread,alive,aliveTime, queue);

    }

    @Test
    void  executeRunnable() throws InterruptedException {
        var minThread = 10;
        var maxThread = 10;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);


        var threadPoolExecutor = new ThreadPoolExecutor(minThread,maxThread,alive,aliveTime, queue);


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
}
