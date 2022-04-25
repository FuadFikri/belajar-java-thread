package com.syamsudinfikri.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        while (!future.isDone()) {
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

    @Test
    public void invokeAllTest() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            Thread.sleep(value * 100l);
            return String.valueOf(value);
        }).collect(Collectors.toList());

        List<Future<String>> futures = executor.invokeAll(callables);
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

    }


    @Test
    public void invokeAny() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            Thread.sleep(value * 1000L);
            return String.valueOf(value);
        }).collect(Collectors.toList());

        String future = executor.invokeAny(callables);
        System.out.println(future);
    }





}
