package org.example.multithreading.reentrantlock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Example1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EvenNumberGenerator generator = new EvenNumberGenerator();

        Runnable generateTask = () -> IntStream.range(0, 100)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + generator.generate()));

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(generateTask).get();
        }

        executorService.shutdown();
    }

    private static class EvenNumberGenerator {
        private int number;
        private final Lock lock;

        public EvenNumberGenerator() {
            this.number = -2;
            this.lock = new ReentrantLock();
        }

        public int generate() {
            lock.lock();
            try {
                return number += 2;
            } finally {
                lock.unlock();
            }
        }
    }
}
