package org.example.leetcode;

public class ReverseBits {
    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(reverseBits(0x0001)));

    }
    public static int reverseBits(int n) {
        return Integer.reverse(n);
    }
    public static int reverseBits2(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= (n & 1);
            n >>= 1;
        }
        return result;
    }
}
