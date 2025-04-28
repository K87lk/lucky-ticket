package org.example.arrays;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        array.add(2);
        array.add(23);
        array.add(3);
        array.add(5);
        array.add(54);
        array.add(7);
        System.out.println(array.contains(1));


    }
}
