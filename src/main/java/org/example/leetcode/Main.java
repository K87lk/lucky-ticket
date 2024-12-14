package org.example.leetcode;

public class Main {
    public static void main(String[] args) {
        countLuckyTickets();
        countLuckyTicketsOptimized();
    }
    public static void countLuckyTickets() {

        long startTime = System.nanoTime();
        int count = 0;

        for (int i = 0; i <= 999999; i++) {
            String ticket = String.format("%06d", i);

            int sumFirstHalf = ticket.charAt(0) - '0' + ticket.charAt(1) - '0' + ticket.charAt(2) - '0';
            int sumSecondHalf = ticket.charAt(3) - '0' + ticket.charAt(4) - '0' + ticket.charAt(5) - '0';

            if (sumFirstHalf == sumSecondHalf) {
                count++;
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("Number of lucky tickets: " + count);
        System.out.println("Method duration: " + duration + " ms");
    }
    public static void countLuckyTicketsOptimized() {
        long startTime = System.nanoTime();


        int[] sumCounts = new int[28];

        for (int i = 0; i <= 999; i++) {
            int sum = (i / 100) + (i / 10 % 10) + (i % 10);
            sumCounts[sum]++;
        }

        int count = 0;

        for (int i = 0; i <= 27; i++) {
            count += (int) Math.pow(sumCounts[i], 2);
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("Number of lucky tickets: " + count);
        System.out.println("Method duration: " + duration + " ms");
    }
}