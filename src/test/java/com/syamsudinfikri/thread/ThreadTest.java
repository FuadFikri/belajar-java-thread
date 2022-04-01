package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;


public class ThreadTest {

    @Test
    public void mainThread() {
        System.out.println(Thread.currentThread().getName()); //output : main
    }


    @Test
    public void createThread(){
        Runnable runnable = () -> System.out.println("Hello form thread : " + Thread.currentThread().getName());

        Thread thread1 = new Thread(runnable);
        thread1.start();

        Thread thread2 = new Thread(() -> System.out.println("Hi from thread " + Thread.currentThread().getName()));
        System.out.println(thread2.getName());
    }

    @Test
    public void threadSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello from thread : " + Thread.currentThread().getName());
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();


        System.out.println("program selesai");

        Thread.sleep(4000L);
    }

    @Test
    public void threadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello from thread : " + Thread.currentThread().getName());
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();
        System.out.println("menunggu selesai");
        thread1.join();
        System.out.println("program selesai");
    }

    @Test
    public void threadInterrupt() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("runnable :" +i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;  // ketika ada interrupt maka berhenti
                }

//                real case gapakai thread sleep
//                dicek menggunakan interrupted()
//                if (Thread.interrupted()){
//                    return;
//                }
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread.sleep(5000L);
        thread1.interrupt(); // setelah 5 detik, lakukan interrupt agar runnable dihentikan
        System.out.println("menunggu selesai");
        thread1.join();
        System.out.println("program selesai");
    }

}
