package org.example.multithreading.condition;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<T> {
    private LinkedBlockingQueue<T> queue;
    private int capacity;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedBlockingQueue<>(capacity);
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return queue.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    public boolean isFull() {
        lock.lock();
        try {
            return capacity == queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void put(T t) throws InterruptedException {
       lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            queue.put(t);
            capacity++;
            System.out.printf("%s was put into the buffer. Thread - %s \n", t, Thread.currentThread().getName());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            T result = queue.take();
            capacity--;
            System.out.printf("%s was taken from the buffer. Thread - %s \n", result, Thread.currentThread().getName());
            notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            return queue.toString();
        } finally {
            lock.unlock();
        }
    }
}
