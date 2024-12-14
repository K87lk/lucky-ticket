package org.example.leetcode;

public class PowerOfFour {
    public static void main(String[] args) {

        int powerOfFour = 256;

    }

    public static boolean isPowerOfFour(int n) {
        if(n == 1) return true;
        if(n < 1 || n % 4 != 0) return false;

        for (int i = 1; i < 31; i++) {
            int result = (int) Math.pow(4, i);
            if(result == n) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPowerOfFourRecursive(int n) {
        if(n == 1) return true;
        if(n < 1 || n % 4 != 0) return false;
        return isPowerOfFourRecursive(n / 4);
    }

    public static boolean isPowerOfFourBitShift(int n) {
       while(n > 0 && n % 4 == 0) n >>= 2;
       return n == 1;
    }
}
