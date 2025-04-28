package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumbersWithoutDuplicateDigits {

    /* Напишите код, который для заданного диапазона последовательных чисел [min; max] возвращает только те числа,
 в которых не повторяется ни одна цифра. Например, если задан диапазон [98; 103] то код должен вернуть числа (98, 102, 103)
 и не возвращает (99, 100, 101). */

    public static void main(String[] args) {
        long min = 0;
        long max = 9999999999999999L;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        long start3 = System.currentTimeMillis();
        executor.submit(() -> findUniqueDigitNumbers(min, max));
        executor.shutdown();
        long end3 = System.currentTimeMillis();

        System.out.println("Time: " + (end3 - start3) + " ms");

        long start4 = System.currentTimeMillis();
        ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
        virtualExecutor.submit(() -> findUniqueDigitNumbers(min, max));
        virtualExecutor.shutdown();
        long end4 = System.currentTimeMillis();

        System.out.println("Time: " + (end4 - start4) + " ms");

        executor.shutdown();

    }

    public static List<Long> findUniqueDigitNumbers(long min, long max) {
        List<Long> uniqueNumbers = new ArrayList<>();

        for (long number = min; number <= max; number++) {
            if (hasUniqueDigits(number)) {
                uniqueNumbers.add(number);
            }
        }
        return uniqueNumbers;
    }

    public static List<Long> findUniqueDigitNumbers2(long min, long max) {
        List<Long> uniqueNumbers = new ArrayList<>();

        for (long number = min; number <= max; number++) {
            if (hasUniqueDigitsWithBitManipulation(number)) {
                uniqueNumbers.add(number);
            }
        }
        return uniqueNumbers;
    }

    private static boolean hasUniqueDigits(long number) {
        boolean[] seen = new boolean[10];
        while (number > 0) {
            int digit = (int) (number % 10);
            if (seen[digit]) {
                return false;
            }
            seen[digit] = true;
            number /= 10;
        }
        return true;
    }

    private static boolean hasUniqueDigitsWithBitManipulation(long number) {
        int bitmask = 0; // Initialize bitmask to track digits
        while (number > 0) {
            long digit = number % 10; // Extract the last digit
            int bit = 1 << digit; // Get the bit corresponding to this digit
            if ((bitmask & bit) != 0) { // Check if the bit is already set
                return false; // Duplicate digit found
            }
            bitmask |= bit; // Set the bit for this digit
            number /= 10; // Remove the last digit
        }
        return true; // No duplicate digits found
    }
}
