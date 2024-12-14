package org.example.leetcode;

import java.util.stream.IntStream;

public class LargestNumberAtLeastTwiceOfOthers {
    public static void main(String[] args) {
        int[] nums = {3, 6, 1, 0};
        int[] nums2 = {1, 2, 3, 4};

        System.out.println(dominantIndex(nums));
        System.out.println(dominantIndex(nums2));

        System.out.println(dominantIndex2(nums));
        System.out.println(dominantIndex2(nums2));
    }
    public static int dominantIndex(int[] nums) {
       int max = 0;
       int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        int secondMax = max / 2;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == max) {
                continue;
            }
            if(nums[i] > secondMax) {
                return -1;
            }
        }
        return index;
    }
    public static int dominantIndex2(int[] nums) {

        int max = 0;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] * 2 > max && i != index) {
                return -1;
            }
        }
        return index;
    }

}
