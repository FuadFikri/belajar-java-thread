package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;


//    tidak direkomendasikan
    @Test
    void manual() throws InterruptedException {
        var thread1 = new Thread(() -> {
            while (message == null) {
                System.out.println("waiting...");
            }
            System.out.println(message);
        });

        var thread2 = new Thread(() -> {
            message = "Halooo";
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
