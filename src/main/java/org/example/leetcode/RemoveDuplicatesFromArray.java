package org.example.leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class RemoveDuplicatesFromArray {
    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {

        int[] resultNms = new int[nums.length];

        Set<Integer> set = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());
        return 0;
    }
}
