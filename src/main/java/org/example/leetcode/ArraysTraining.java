package org.example.leetcode;

import java.util.Random;
import java.util.stream.IntStream;

public class ArraysTraining {
    public static void main(String[] args) {
        int[] numbers = IntStream.generate(() -> new Random().nextInt(1_000_000_000)).limit(10000).toArray();
        int[] sortedNumbers = {1, 3, 6, 7, 12, 17, 34, 53, 73};
        int[] arrayWithZeros = {0, 5, 0, 34, 0, 12, 36, 0, 0, 23, 12, 54};

        String word = "madam";

        System.out.println(isPalindrome(word));
        System.out.println(isPalindrome2(word));

    }

    public static int[] insert() {
        int[] array = new int[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int[] insertAtPosition(int[] array, int value, int position) {
        for (int i = array.length - 1; i > position; i--) {
            array[i] = array[i - 1];
        }
        array[position] = value;
        return array;
    }

    public static int[] removeAtPosition(int[] numbers, int indexToRemove) {
        if (indexToRemove >= 0 && indexToRemove < numbers.length) {
            for (int i = indexToRemove; i < numbers.length - 1; i++) {
                numbers[i] = numbers[i + 1];
            }
            numbers[numbers.length - 1] = 0;
        }
        return numbers;
    }

    public static int searchIndex(int[] numbers, int value) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int iterativeBinarySearch(int[] input, int value) {

        int start = 0;
        int end = input.length - 1;

        while (start <= end) {
            int midpoint = (start + end) / 2;
            if (input[midpoint] == value) {
                return midpoint;
            } else if (input[midpoint] < value) {
                start = midpoint + 1;
            } else {
                end = midpoint - 1;
            }
        }
        return -1;
    }

    public static int[] bubbleSort(int[] nums) {
        long start = System.nanoTime();

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        long end = System.nanoTime();
        double durationInSeconds = (double) (end - start) / 1_000_000_000;

        System.out.println("Method duration: " + durationInSeconds + " sec");
        return nums;
    }

    public static int findMin(int[] nums) {
        int min = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }

    public static int findSecondMax(int[] arr) {

        int max = Integer.MIN_VALUE;
        int second_max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                second_max = max;
                max = arr[i];
            } else if (arr[i] > second_max && arr[i] != max) {
                second_max = arr[i];
            }
        }
        return second_max;
    }

    public static int[] moveZerosToEnd(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0 && arr[j] == 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            if (arr[j] != 0) {
                j++;
            }
        }
        return arr;
    }

    public static int[] reverseElements(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        return arr;
    }

    public static boolean isPalindrome(String word) {
        int start = 0;
        int end = word.length() - 1;
        char[] chars = word.toCharArray();

        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isPalindrome2(String word) {

        StringBuilder sb = new StringBuilder(word);
        String reversed = sb.reverse().toString();

        return word.equalsIgnoreCase(reversed);
    }

}
