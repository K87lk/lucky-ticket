package org.example.multithreading.condition;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);

        Runnable producingTask = () -> IntStream.iterate(0, i -> i + 1).limit(10).forEach(i -> {
            try {
                buffer.put(i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
        });

        Runnable consumingTask = () -> IntStream.iterate(0, i -> i + 1).limit(10).forEach(i -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    buffer.take();
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(producingTask);
        executorService.submit(consumingTask);

        executorService.shutdown();

    }
}
