package org.example.leetcode;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class Factorial {
    public static void main(String[] args) {

//        long start = System.currentTimeMillis();
//        System.out.println(factorialParallel(10));
//        long end = System.currentTimeMillis();
//
//        System.out.println("Total time: " + (end - start) + " ms");


        long start2 = System.currentTimeMillis();
        System.out.println(factorialParallel2(100));
        long end2 = System.currentTimeMillis();

        System.out.println("Total time: " + (end2 - start2) + " ms");
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static long factorialIterative(int n) {
        Map<Integer, Long> cache = new HashMap<>();

        if (n < 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (n == 1 || n == 0) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            if (cache.containsKey(n)) {
                return cache.get(n);
            }
            result *= i;
            cache.put(i, result);
        }
        return result;
    }
    public static long factorialParallel(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        int mid = n / 2;

        var future1 = CompletableFuture.supplyAsync(() -> computeRange(1, mid));
        var future2 = CompletableFuture.supplyAsync(() -> computeRange(mid + 1, n));

        return future1.thenCombine(future2, (a, b) -> a * b).join();
    }

    public static BigInteger factorialParallel2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }

        int mid = n / 2;

        var future1 = CompletableFuture.supplyAsync(() -> computeRange2(1, mid));
        var future2 = CompletableFuture.supplyAsync(() -> computeRange2(mid + 1, n));

        return future1.thenCombine(future2, BigInteger::multiply).join();
    }

    private static long computeRange(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .asLongStream()
                .reduce(1, (a, b) -> a * b);
    }

    private static BigInteger computeRange2(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
