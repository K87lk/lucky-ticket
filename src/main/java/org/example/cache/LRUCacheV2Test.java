package org.example.cache;

public class LRUCacheV2Test {
    public static void main(String[] args) {
        LRUCacheV2<Integer, String> cache = new LRUCacheV2<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCache(); // 3=C 2=B 1=A

        cache.get(1); // Используем 1
        cache.put(4, "D"); // Удалится 2
        cache.printCache(); // 4=D 1=A 3=C
    }
}
