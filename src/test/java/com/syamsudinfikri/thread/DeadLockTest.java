package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

public class DeadLockTest {


    @Test
    public void transferDeadlock() throws InterruptedException {
        var balance1 = new Balance(1_000_000L);
        var balance2 = new Balance(1_000_000L);

        //        transfer dari akun 1 ke akun 2
        var thread1 = new Thread(() -> {
            try {
                Balance.transferDeadlock(balance1,balance2, 500_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


//        transfer dari akun 2 ke akun 1
        var thread2 = new Thread(() -> {
            try {
                Balance.transferDeadlock(balance2,balance1, 500_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(balance1.getValue());
        System.out.println(balance2.getValue());

    // tidak ada output
//        karena saling tunggu
    }

    @Test
    public void transfer() throws InterruptedException {
        var balance1 = new Balance(1_000_000L);
        var balance2 = new Balance(1_000_000L);

        //        transfer dari akun 1 ke akun 2
        var thread1 = new Thread(() -> {
            try {
                Balance.transfer(balance1,balance2, 500_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


//        transfer dari akun 2 ke akun 1
        var thread2 = new Thread(() -> {
            try {
                Balance.transferDeadlock(balance2,balance1, 500_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(balance1.getValue()); //output 1000000
        System.out.println(balance2.getValue()); //output 1000000


    }
}
