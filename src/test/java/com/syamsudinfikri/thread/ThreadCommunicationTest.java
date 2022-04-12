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


    @Test
    void waitAndNotify() throws InterruptedException {
        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    System.out.println("wait");
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        var thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Fikri";
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

    @Test
    void waitAndNotifyAll() throws InterruptedException {
        final Object lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    System.out.println("wait thread 1");
                    lock.wait();
                    System.out.println("thread 1 :"+message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread9 = new Thread(() -> {
            synchronized (lock){
                try {
                    System.out.println("wait thread 9");
                    lock.wait();
                    System.out.println("thread 9 :"+  message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        var thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Fikri";
                lock.notifyAll();
            }
        });

        thread1.start();
        thread9.start();

        thread2.start();

        thread1.join();
        thread9.join();

        thread2.join();

    }
}
