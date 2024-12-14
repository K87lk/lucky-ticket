package org.example.leetcode;

public class FibonacciNumber {
    public static void main(String[] args) {

    }

    public static int fibRec(int n) {
        if (n <= 1) return n;

        return fibRec(n - 1) + fibRec(n - 2);
    }

    public static int fibDynamic(int n) {
        if (n <= 1) return n;

        int[] fibNums = new int[n + 1];
        fibNums[0] = 0;
        fibNums[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibNums[i] = fibNums[i - 1] + fibNums[i - 2];
        }
        return fibNums[n];
    }

}
