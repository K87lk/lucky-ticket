package org.example.leetcode.trees;

import org.example.leetcode.trees.Node;

public class SinglyLinkedList {
    private Node head;

    public void insert(int data) {

        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void delete(int data) {

        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node temp = head;
        Node prev = null;
        while (temp != null && temp.data != data) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println(data + " not found in the list");
            return;
        }

        prev.next = temp.next;

    }
}
