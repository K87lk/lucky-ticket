package org.example.leetcode;

public class CustomHashMapGeneric<K, V> {
    private static final int SIZE = 1000; // Size of the array
    private Bucket<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public CustomHashMapGeneric() {
        buckets = new Bucket[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new Bucket<>();
        }
    }

    public void put(K key, V value) {
        int index = hash(key);
        buckets[index].put(key, value);
    }

    public V get(K key) {
        int index = hash(key);
        return buckets[index].get(key);
    }

    public void remove(K key) {
        int index = hash(key);
        buckets[index].remove(key);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % SIZE;
    }

    private static class Bucket<K, V> {
        private Node<K, V> head;

        public void put(K key, V value) {
            Node<K, V> prev = find(key);
            if (prev == null) {
                Node<K, V> newNode = new Node<>(key, value);
                newNode.next = head;
                head = newNode;
            } else {
                prev.value = value;
            }
        }

        public V get(K key) {
            Node<K, V> node = find(key);
            return node == null ? null : node.value;
        }

        public void remove(K key) {
            Node<K, V> dummy = new Node<>(null, null);
            dummy.next = head;
            Node<K, V> prev = dummy;

            while (prev.next != null) {
                if (prev.next.key.equals(key)) {
                    prev.next = prev.next.next;
                    break;
                }
                prev = prev.next;
            }

            head = dummy.next;
        }

        private Node<K, V> find(K key) {
            Node<K, V> current = head;
            while (current != null) {
                if (current.key.equals(key)) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }

        private static class Node<K, V> {
            K key;
            V value;
            Node<K, V> next;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
