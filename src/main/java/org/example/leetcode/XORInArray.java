package org.example.leetcode;

import java.util.Arrays;

public class XORInArray {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 34, 56, 12};
        int[] xorNUms = new int[nums.length];

        for (int i = 0; i < nums.length - 1; i++) {
            xorNUms[i] = nums[i] ^ nums[i + 1];
        }

        System.out.println(Arrays.toString(xorNUms));
    }
    public static int xorOperation(int n, int start) {
        return 0;
    }
}
