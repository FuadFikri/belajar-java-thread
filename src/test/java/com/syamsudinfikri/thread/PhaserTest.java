package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {


    @Test
    void asCyclicBarrier() throws InterruptedException {
        final var phaser = new Phaser();
        final var executorService = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);

        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                phaser.arriveAndAwaitAdvance();
                System.out.println("done");
            });
        }


        executorService.awaitTermination(1, TimeUnit.DAYS);
    }


    @Test
    void asCountDownLatch() throws InterruptedException {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);


        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("start task");
                    Thread.sleep(2000);
                    System.out.println("end task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    phaser.arrive(); //menurunkan counter
                }
            });
        }

        executor.execute(() -> {
            phaser.awaitAdvance(0);
            System.out.println("all tasks done");
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
