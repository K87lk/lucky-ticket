package org.example.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheckIfExists {
    public static void main(String[] args) {
        int[] nums = {10, 2, 5, 3};

        System.out.println(checkIfExist(nums));
        System.out.println(checkIfExistWithBinarySearch(nums));
        System.out.println(checkIfExistWithHashSet(nums));
    }

    public static boolean checkIfExist(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j] * 2 && i != j) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkIfExistWithBinarySearch(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int target = 2 * nums[i];
            int low = 0;
            int high = nums.length -1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if(nums[mid] == target && mid != i) {
                    return true;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
    public static boolean checkIfExistWithHashSet(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num: nums) {
            if(set.contains(num * 2) || (num % 2 == 0 && set.contains(num / 2))) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

}
