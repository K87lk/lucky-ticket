package org.example.leetcode;

public class PowerOfTwo {
    public static void main(String[] args) throws NoSuchMethodException {

    }
    public static boolean isPowerOfTwoRec(int n) {
        if(n < 1) return false;

        return n % 2 == 0 && isPowerOfTwoRec(n / 2);
    }
    public static boolean isPowerOfTwoBitCount(int n) {
        if(n < 1) return false;

        int bitCount = Integer.bitCount(n);
        return bitCount == 1;
    }
    public static boolean isPowerOfTwoWithMathPower(int n) {
        if(n < 1) return false;

        for (int i = 1; i < 31; i++) {
            int result = (int) Math.pow(2, i);
            if(result == n) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPowerOfTwoWithLeftBitShift(int n) {
        int x = 1;

        while (x <= n) {
            if(x == n) return true;
            if(x > Integer.MAX_VALUE / 2) break;
            x = x << 1;
        }
        return false;
    }

}
