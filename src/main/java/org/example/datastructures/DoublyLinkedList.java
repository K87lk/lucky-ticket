package org.example.datastructures;

public class DoublyLinkedList {
    private class DNode {
        private int data;
        private DNode next;
        private DNode prev;

        public DNode(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    private DNode head;
    private DNode tail;
    private int size;

    public int size() {
        return size;
    }

    public void addFirst(int data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    public void addLast(int data) {
        DNode newNode = new DNode(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    public void deleteByValue(int data) {
        if (head == null) {
            return;
        }
        if (head.data == data) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            else {
                tail = null;
            }
            return;
        }
        DNode current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }
        if (current == null) {
            return;
        }
        if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }
}
