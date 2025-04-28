package org.example.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpiryCache<K, V> {
    private static class Entry<V> {
        V value;
        long expiryTime;

        public Entry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }
    }
    private final Map<K, Entry<V>> cache = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private final long deltaTime;

    public ExpiryCache(long cleanupInterval, long deltaTime) {
        this.deltaTime = deltaTime;
        executorService.scheduleAtFixedRate(this::cleanUp, cleanupInterval, cleanupInterval, TimeUnit.MILLISECONDS);
    }

    public void put (K key, V value) {
        long expiryTime = System.currentTimeMillis() + deltaTime;
        cache.put(key, new Entry<>(value, expiryTime));
    }

    public V get (K key) {
        Entry<V> entry = cache.get(key);
        if (entry == null || System.currentTimeMillis() > entry.expiryTime) {
            cache.remove(key);
            return null;
        }
        return entry.value;
    }

    private void cleanUp() {
        long now = System.currentTimeMillis();
        cache.entrySet().removeIf(e -> e.getValue().expiryTime <= now);
    }

    public void shutDown() {
        executorService.shutdown();
    }

}
