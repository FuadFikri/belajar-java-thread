package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {


//   run in  one thread only
    @Test
    public void singleThread() throws InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("runnable "+ finalI +" in thread : "+ Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }



    @Test
    public void fixedThread() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("runnable "+ finalI +" in thread : "+ Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    public void cacheThread() throws InterruptedException {
        var executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("runnable "+ finalI +" in thread : "+ Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
//            System.out.println("====");
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

}
