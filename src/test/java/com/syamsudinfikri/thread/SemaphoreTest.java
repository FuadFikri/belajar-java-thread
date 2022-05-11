package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {


    @Test
    public void create() throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.execute(() -> {

                try{
                    semaphore.acquire();
                    Thread.sleep(2000);
                    System.out.println("finish "+ finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();

                }

            });
        }


        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
