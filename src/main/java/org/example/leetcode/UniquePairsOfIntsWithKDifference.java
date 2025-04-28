package org.example.leetcode;

import java.util.*;

public class UniquePairsOfIntsWithKDifference {
    public static void main(String[] args) {
        int[] array = {1, 7, 5, 9, 2, 12, 3};
        int k = 2;
        System.out.println(countKDifference2(array, k));

    }
    public static int countKDifference(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (set.contains(num + k)) {
                count++;
            }
        }
        return count;
    }

    public static Map<Integer, Integer> countKDifference2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0 || k < 0) {
            return new HashMap<>();
        }
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (set.contains(num + k)) {
                map.put(num, num + k);
            }
        }
        return map;
    }
}
