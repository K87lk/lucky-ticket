package org.example.leetcode;

public class BitwiseOperations {
    public static void main(String[] args) {
        int x = 5;
        int y = 6;

        String binaryX = Integer.toBinaryString(x);
        String binaryY = Integer.toBinaryString(y);

        System.out.println(binaryX + " & " + binaryY + " = " + (x & y));
        int and = 1;

        for (int i = 0; i < x; i++) {
            and <<= 6;
            System.out.println(and);
        }
    }
}
