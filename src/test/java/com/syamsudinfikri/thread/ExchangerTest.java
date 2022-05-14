package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {


    @Test
    void test() throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> {
            try {
                var result = exchanger.exchange("First");
                System.out.println("Thread 1 receive : " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executor.execute(() -> {
            try {
                var result = exchanger.exchange("second");
                System.out.println("Thread 2 receive : " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
