package org.example.leetcode;

import java.util.List;
import java.util.stream.IntStream;

public class NumbersWithoutDuplicateDigits2 {
    public static void main(String[] args) {
        int min = 99;
        int max = 105;
        System.out.println(returnNonRepeatingDigitNumbers(min, max));
    }

    public static List<Integer> returnNonRepeatingDigitNumbers(int min, int max) {
        return IntStream.rangeClosed(min, max)
                .filter(NumbersWithoutDuplicateDigits2::hasUniqueDigits)
                .boxed()
                .toList();
    }

    private static boolean hasUniqueDigits(int number) {
        boolean[] digitSeen = new boolean[10];
        while (number > 0) {
            int digit = number % 10;
            if (digitSeen[digit]) {
                return false;
            }
            digitSeen[digit] = true;
            number /= 10;
        }
        return true;
    }
}
