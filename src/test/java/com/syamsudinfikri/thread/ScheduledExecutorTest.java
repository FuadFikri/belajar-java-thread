package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    @Test
    public void delayedJob() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);

//        dieksekusi setelah 2 detik
        var future = executor.schedule(() -> System.out.println("hello"), 2, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.SECONDS));
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    public void periodicJob() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);

//        dieksekusi setelah 2 detik
        var future = executor.scheduleAtFixedRate(() -> System.out.println("hello periodic"), 5, 2,TimeUnit.SECONDS);

        System.out.println("delay : " + future.getDelay(TimeUnit.SECONDS));
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
