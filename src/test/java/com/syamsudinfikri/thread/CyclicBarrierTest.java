package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {


    @Test
    public void test5Thread() throws InterruptedException {
        final var cyclic = new CyclicBarrier(5);
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("waiting");
                    cyclic.await();
                    System.out.println("done waiting");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

            });
        }


        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    public void test4Thread() throws InterruptedException {
        final var cyclic = new CyclicBarrier(5);
        final var executor = Executors.newFixedThreadPool(10);

        // akan selalu waiting karena hanya ada 4 thread, sedangkan cyclicBarrier ada 5
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("waiting");
                    cyclic.await();
                    System.out.println("done waiting");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

            });
        }


        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
