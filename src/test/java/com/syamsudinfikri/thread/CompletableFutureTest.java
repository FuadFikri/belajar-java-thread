package com.syamsudinfikri.thread;


import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTest {


    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private Random random = new Random();

    public Future<String> getValue() {
        CompletableFuture<String> future = new CompletableFuture<>();

        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                future.complete("selesai");
            } catch (InterruptedException e) {
                e.printStackTrace();
                future.completeExceptionally(e);
            }

        });

        return future;
    }

    @Test
    public void getValueTest() throws ExecutionException, InterruptedException {
        System.out.println(getValue().get());
    }

    private Future<String> execute(CompletableFuture<String> future ,String value) {


        executorService.execute(() -> {
            try {
                Thread.sleep(500 + random.nextInt(5000));
                future.complete("selesai :  " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
                future.completeExceptionally(e);
            }

        });

        return future;
    }

    @Test
    public void testFastest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

//        menjalankan beberapa proses dan mengambil yang paling cepat
        this.execute(future," Thread A");
        this.execute(future," Thread B");
        this.execute(future," Thread C");

        System.out.println(future.get());
    }


}
