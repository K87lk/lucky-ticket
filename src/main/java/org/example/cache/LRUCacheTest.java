package org.example.cache;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache);

        cache.get(1); // Используем ключ 1 -> он становится "свежим"
        cache.put(4, "D"); // Добавляем новый элемент, удаляется наименее используемый (2)

        System.out.println(cache); // {3=C, 1=A, 4=D}
    }
}
