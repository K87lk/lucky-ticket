package org.example.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,5,5,11};
        int[] nums2 = {2,7,11,15};

        System.out.println(Arrays.toString(twoSum(nums, 10)));
        System.out.println(Arrays.toString(twoSumWithHashMap(nums2, 9)));
    }
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target && i != j) {
                    return new int[]{i , j};
                }
            }
        }
        return new int[]{};
    }
    public static int[] twoSumWithHashMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
