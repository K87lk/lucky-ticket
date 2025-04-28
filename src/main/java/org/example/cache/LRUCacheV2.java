package org.example.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheV2<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> list;

    public LRUCacheV2(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!cache.containsKey(key)) return null;
        Node<K, V> node = cache.get(key);
        list.moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            list.moveToHead(node);
        } else {
            if (cache.size() >= capacity) {
                Node<K, V> tail = list.removeTail();
                cache.remove(tail.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            list.addToHead(newNode);
            cache.put(key, newNode);
        }
    }

    public void printCache() {
        list.print();
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private static class DoublyLinkedList<K, V> {
        private final Node<K, V> head, tail;

        public DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addToHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void moveToHead(Node<K, V> node) {
            removeNode(node);
            addToHead(node);
        }

        Node<K, V> removeTail() {
            Node<K, V> last = tail.prev;
            removeNode(last);
            return last;
        }

        void print() {
            Node<K, V> cur = head.next;
            while (cur != tail) {
                System.out.print(cur.key + "=" + cur.value + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
}
