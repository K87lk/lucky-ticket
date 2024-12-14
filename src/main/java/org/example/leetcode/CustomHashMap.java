package org.example.leetcode;

public class CustomHashMap {
    private static final int SIZE = 1000;
    private Bucket[] buckets;

    public CustomHashMap() {
        buckets = new Bucket[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new Bucket();
        }
    }

    private static class Bucket {
        private Node head;

        public void put(int key, int value) {
            Node prev = find(key);
            if (prev == null) {
                Node newNode = new Node(key, value);
                newNode.next = head;
                head = newNode;
            } else {
                prev.value = value;
            }
        }

        public int get(int key) {
            Node node = find(key);
            return node == null ? -1 : node.value;
        }

        public void remove(int key) {
            Node dummy = new Node(-1, -1);
            dummy.next = head;
            Node prev = dummy;

            while (prev.next != null) {
                if (prev.next.key == key) {
                    prev.next = prev.next.next;
                    break;
                }
                prev = prev.next;
            }

            head = dummy.next;
        }

        private Node find(int key) {
            Node current = head;
            while (current != null) {
                if (current.key == key) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
    }

    private static class Node {
        private int key;
        private int value;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}


