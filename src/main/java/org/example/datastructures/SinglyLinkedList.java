package org.example.datastructures;

import java.util.NoSuchElementException;

public class SinglyLinkedList {

    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public int indexOf(int data) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.value == data) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public void addFirst(int data) {
        Node newNode = createNode(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
           newNode.next = head;
           head = newNode;
        }
        size++;
    }

    private Node createNode(int data) {
        return new Node(data);
    }

    public void addLast(int data) {
        Node newNode = createNode(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (head == tail) {
            head = tail = null;
            return;
        }

          Node second = head.next;
          head.next = null;
          head = second;
          size--;
    }
    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (head == tail) {
            head = tail = null;
            return;
        }

        tail.next = null;
        tail = getPrevious(tail);
        size--;
    }

    private Node getPrevious(Node node) {
        Node current = head;
        while (current != null) {
            if (current.next == node) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean contains(int data) {
        return indexOf(data) != -1;
    }

    public boolean isEmpty() {
        return head == null;
    }
    public int[] toArray() {
        int[] result = new int[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            result[index] = current.value;
            current = current.next;
            index++;
        }
        return result;
    }
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.print("null" + "\n");
    }
/*
*
*  10 -> 20 -> 30
*  p      c     n
*
* */
    public void reverse() {
        var prev = head;
        var current = head.next;

        tail = head;
        tail.next = null;

        while (current != null) {
            var next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    public int getKthFromTheEnd(int k) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        if (k < 1) {
            throw new IllegalStateException();
        }
        Node left = head;
        Node right = head;

        for (int i = 0; i < k - 1; i++) {
            right = right.next;
            if (right == null) {
                throw new IllegalStateException();
            }
        }
        while (right != tail) {
            left = left.next;
            right = right.next;
        }
        return left.value;
    }
}



