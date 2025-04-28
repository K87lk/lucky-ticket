package org.example.multithreading.condition;

import java.util.concurrent.*;

public class Example2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Warehouse<Integer> warehouse = new Warehouse<>(50);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable producerTask = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    warehouse.store(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumerTask = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    warehouse.take();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        executorService.submit(producerTask);
        executorService.submit(consumerTask);

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks done...");
    }
}
