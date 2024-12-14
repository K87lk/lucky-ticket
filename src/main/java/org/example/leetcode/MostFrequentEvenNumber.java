package org.example.leetcode;

import java.util.*;

public class MostFrequentEvenNumber {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,4,4,1};

        System.out.println(mostFrequentEven(nums));
    }
    public static int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (num % 2 == 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        int result = 0;
        int count = 0;

        for (var entry: map.entrySet()) {
            if(count == entry.getValue()) {
                result = Math.min(result, entry.getKey());
            }
            if(entry.getKey() > count) {
                count = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}
