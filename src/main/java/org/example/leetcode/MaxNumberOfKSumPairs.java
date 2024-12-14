package org.example.leetcode;

import java.util.Arrays;

public class MaxNumberOfKSumPairs {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        System.out.println(maxOperations(nums, 5));
    }
    public static int maxOperations(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= k) {
                nums[i] = 0;
            }
        }

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int result = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if(sum == k) {
                result++;
                left++;
                right--;
            } else if (sum > k) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }
}
