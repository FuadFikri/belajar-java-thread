package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest {


    @Test
    public void create() throws InterruptedException, ExecutionException {
        var executor = Executors.newSingleThreadExecutor();
        var future = executor.submit(() -> {
            System.out.println("long process .....");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           return "Hi... ";
        });

//        isDone -> mengecek apakah future sudah selesai
        while (!future.isDone()){
            System.out.println("waiting result");
            Thread.sleep(1000);
        }


//        get() mengambil result data, jika belum ada maka akan menunggu sampai ada

        System.out.println(future.get());
    }



    @Test
    public void createAndTimeout() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();
        var future = executor.submit(() -> {
            System.out.println("long process .....");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi... ";
        });

        String f = null;
        try {
            f = future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("time out");
        }
        System.out.println(f);
    }


    @Test
    public void cancel() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();
        var future = executor.submit(() -> {
            System.out.println("long process .....");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi... ";
        });

        Thread.sleep(2000);
        future.cancel(true);

        System.out.println(future.isDone());
        System.out.println(future.isCancelled());

//        tidak bisa melakukan get karena sudah dicancel
        String value = future.get();
        System.out.println(value);
    }




}
