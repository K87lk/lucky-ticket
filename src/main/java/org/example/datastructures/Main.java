package org.example.datastructures;

public class Main {
    public static void main(String[] args) {
       SinglyLinkedList list = new SinglyLinkedList();
       list.addLast(1);
       list.addLast(2);
       list.addLast(3);
       list.addLast(4);
       list.addLast(5);

       list.print();

        System.out.println(list.getKthFromTheEnd(5));
    }
}
