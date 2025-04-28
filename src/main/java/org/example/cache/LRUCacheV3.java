package org.example.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCacheV3<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, V> map;
    private final LinkedBlockingDeque<K> queue;
    private final ReadWriteLock lock;

    public LRUCacheV3(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>(capacity);
        this.queue = new LinkedBlockingDeque<>(capacity);
        this.lock = new ReentrantReadWriteLock();
    }

    public V get(K key) {
        lock.readLock().lock();

        try {
            if (map.containsKey(key)) {
                queue.remove(key);
                queue.addFirst(key);
                return map.get(key);
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) throws InterruptedException {
        lock.writeLock().lock();
        try {
            if (map.containsKey(key)) {
                queue.remove(key);
            } else if (map.size() >= capacity) {
                K last = queue.pollLast();
                map.remove(last);
            }
            queue.addFirst(key);
            map.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
