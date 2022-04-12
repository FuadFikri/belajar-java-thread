package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    public void delayedJob() throws InterruptedException {
        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(" delayed job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 5000L); //delay 5 detik

        Thread.sleep(6000L);
    }

    @Test
    public void periodicJob() throws InterruptedException {
        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(" periodic job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 3000L, 2000L);

        Thread.sleep(12000L);
    }
}
