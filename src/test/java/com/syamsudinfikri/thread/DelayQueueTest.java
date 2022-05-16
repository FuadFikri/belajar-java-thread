package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class DelayQueueTest {


    @Test
    void test() throws InterruptedException {
        var queue = new DelayQueue<ScheduledFuture<String>>();
        final var executor = Executors.newFixedThreadPool(20);
        final var executorScheduled = Executors.newScheduledThreadPool(10);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            queue.put(executorScheduled.schedule(() -> "data " + index, i, TimeUnit.SECONDS));
        }

        executor.execute(() -> {
            while (true) {
                try {
                    var data = queue.take();
                    System.out.println("receive : " + data.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
