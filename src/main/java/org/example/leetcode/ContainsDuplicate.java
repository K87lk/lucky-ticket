package org.example.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums2 = {2, 4, 6, 6, 7};

        System.out.println(containsDuplicateUsingSet(nums));
        System.out.println(containsDuplicateUsingSet(nums2));

        containsDuplicateUsingHashMap(nums);
        containsDuplicateUsingHashMap(nums2);
    }
    public static boolean containsDuplicateUsingSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num: nums) {
            if(set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
    public static boolean containsDuplicateUsingHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num: nums) {
            if(map.containsKey(num) && map.get(num) > 1) {
                return true;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return false;
    }
}
