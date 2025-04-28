package org.example.datastructures;

import java.util.Arrays;

public class SimpleMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int size = 0;
    private Node<K, V>[] table;

    public SimpleMap() {
        table = new Node[INITIAL_CAPACITY];
    }

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % table.length;
    }
    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> head = table[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;

        if ((float) size / table.length > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> head = table[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        Node<K, V> head = table[index];
        Node<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    table[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                size--;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    private void resize() {
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length * 2];
        size = 0;

        for (var head: oldTable) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    public void printHashMap() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> head = table[i];
            if (head != null) {
                System.out.print(i + ": ");
                while (head != null) {
                    System.out.print("(" + head.key + " -> " + head.value + ") -> ");
                    head = head.next;
                }
                System.out.println("null");
            }
        }
    }
}
