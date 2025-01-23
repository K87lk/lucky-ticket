package org.example.leetcode;

public class MinSubarrayLength {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println("Smallest Subarray Length: " + minSubArrayLen(target, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int windowSum  = 0;
        int start = 0;

        for (int end = 0; end < nums.length; end++) {
            windowSum += nums[end];

            while (windowSum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                windowSum -= nums[start];
                start++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}


