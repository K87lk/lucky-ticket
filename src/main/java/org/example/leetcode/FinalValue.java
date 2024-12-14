package org.example.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//2154. Keep Multiplying Found Values by Two
public class FinalValue {
    public static void main(String[] args) {
        int[] nums = {5,3,6,1,12};

        System.out.println(findFinalValue(nums, 3));
        System.out.println(findFinalValueWithHashSet(nums, 3));

    }
    public static int findFinalValue(int[] nums, int original) {

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == original) {
                original *= 2;
                i = 0;
            }
        }
        return original;
    }
    public static int findFinalValueWithHashSet(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        while (set.contains(original)) {
            original *= 2;
        }

        return original;
    }

}
