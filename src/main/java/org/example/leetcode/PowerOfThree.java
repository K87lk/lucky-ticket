package org.example.leetcode;

public class PowerOfThree {
    public static void main(String[] args) {

        System.out.println(isPowerOfThreeIterative(125));

    }
    public static boolean isPowerOfThreeIterative(int n) {
        if(n == 1 || n == 3) return true;

        if(n < 1) return false;

        int b = 3;

        for (int i = 2; b <= n; i++) {
            if(b == n) {
                return true;
            }
            else {
                b = (int) Math.pow(3, i);
            }
        }
        return false;
    }
}
