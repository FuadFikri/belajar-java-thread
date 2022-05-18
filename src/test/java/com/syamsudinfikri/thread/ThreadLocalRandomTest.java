package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalRandomTest {


    @Test
    void test() throws InterruptedException {


        var executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                    var nextInt = ThreadLocalRandom.current().nextInt(1000);
                    System.out.println(nextInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }


    @Test
    void stream() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                ThreadLocalRandom.current().ints(1000,0 ,1000)
                        .forEach(System.out::println);
            });
        }
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
