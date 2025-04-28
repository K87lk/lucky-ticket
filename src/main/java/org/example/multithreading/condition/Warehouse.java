package org.example.multithreading.condition;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse<T> {
    private LinkedBlockingQueue<T> items;
    private int capacity;
    private final Lock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public Warehouse(int capacity) {
        this.items = new LinkedBlockingQueue<>(capacity);
        this.capacity = capacity;
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void store(T t) throws InterruptedException {
        lock.lock();
        try {
            while (items.size() == capacity) {
                System.out.println("Warehouse is full. Awaiting...");
                notFull.await();
            }
            items.put(t);
            System.out.println("Item produced: " + t + ", Warehouse size: " + items.size());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (items.isEmpty()) {
                System.out.println("Warehouse is empty. Awaiting...");
                notEmpty.await();
            }
            var item = items.remove();
            System.out.println("Item consumed: " + item.toString() + ", Warehouse size: " + items.size());
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
