package org.example.multithreading.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Example2 {

    private static final int TIMES = 10;

    public static void main(String[] args) {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(createTaskDoingOperationOnCounter(counter, i -> counter.increment(), TIMES));
        executorService.submit(createTaskDoingOperationOnCounter(counter, i -> counter.decrement(), TIMES));

        executorService.shutdown();
    }

    private static Runnable createTaskDoingOperationOnCounter(Counter counter, IntConsumer operation, int times) {
        return () -> {
          counter.lock();
          try {
              IntStream.range(0, times).forEach(operation);
          } finally {
              counter.unlock();
          }
        };
    }

    private static final class Counter {
        private final Lock lock = new ReentrantLock();
        private int value;

        public void lock() {
            lock.lock();
            printCurrentThreadMessage("Thread '%s' acquired a lock. \n");
        }

        public void increment() {
            value++;
            printCurrentThreadMessage("Thread '%s' incremented a value. \n");
        }

        public void decrement() {
            value--;
            printCurrentThreadMessage("Thread '%s' decremented a value. \n");

        }

        public void unlock() {
            printCurrentThreadMessage("Thread '%s' releases a lock. \n");
            lock.unlock();
        }

        private static void printCurrentThreadMessage(String message) {
            System.out.printf(message, Thread.currentThread().getName());
        }
    }
}
