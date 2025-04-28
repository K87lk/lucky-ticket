package org.example.datastructures;

import java.util.Arrays;

public class Stack {
    private int[] items;
    private int count;
    private static final int INITIAL_SIZE = 10;

   public Stack() {
        items = new int[INITIAL_SIZE];
    }

   public Stack(int size) {
        items = new int[size];
    }

    public void push(int item) {
       if (count == items.length) {
           throw new StackOverflowError("Stack is full");
       }
        items[count] = item;
        count++;
    }
    public int pop() {
       if (isEmpty()) {
           throw new IllegalArgumentException("Stack is empty");
       }
       return items[--count];
    }
    public int peek() {
       if (isEmpty()) {
           throw new IllegalStateException();
       }
       return items[count - 1];
    }
    public boolean isEmpty() {
       return count == 0;
    }
    @Override
    public String toString() {
       var content = Arrays.copyOfRange(items, 0, count);
        return Arrays.toString(content);
    }
}
