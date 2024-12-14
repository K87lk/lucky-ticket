package org.example.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.of;

public class TwoPointers {
    public static void main(String[] args) {

        int[] numbers = {3, 5, 12, 2, 6, 90};
        int[] secondArray = {12, 15, 17, 21, 18, 14, 11, 21, 35, 14, 18, 18};
        int[] consArr = {1,2,5,8,-4,-3,7,6,5};
        int[] missing = {1, 2, 4, 5, 6, 7, 8, 0};
//        System.out.println(Arrays.toString(twoSum(numbers, 7)));
//        System.out.println(findMax(numbers));
//
//        System.out.println(Arrays.toString(removeEven(numbers)));
//        System.out.println(findSecondMax(numbers));
//        System.out.println(getLastFive(secondArray));
//        char[] word = {'o', 'l', 'l', 'e', 'h'};
//
//        System.out.println(reverseString(word));
//        invertedPyramid();
//        System.out.println(consecutivePairs(consArr));
//        System.out.println(consecutivePairsIntStream(consArr));
//
//        System.out.println(findMaxIntStream(numbers));

        System.out.println(missingNumber(missing));
    }

    public static int[] twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }

    public static int findMax(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    public static int[] removeEven(int[] arr) {
        return Arrays.stream(arr).filter(i -> i % 2 != 0).toArray();
    }

    public static int findSecondMax(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 2];
    }

    public static boolean validParenthesis(String s) {
        Deque<Character> queue = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                queue.push(c);
            } else {
                if (queue.isEmpty()) {
                    return false;
                }
                char top = queue.pop();

                if (c == ')' && top != '(') {
                    return false;
                } else if (c == '}' && top != '{') {
                    return false;
                } else if (c == ']' && top != '[') {
                    return false;
                }
            }
        }
        return queue.isEmpty();
    }

    // Дан список числовых элементов. Нужно взять из него топ 5, таким образом, что все повторяющиеся значения нужно добавить в этот топ столько раз, сколько они встречаются в списке.
// Пример ввода/вывода:
// 12, 15, 17, 21, 18, 14, 11, 21, 35, 14, 18, 18
// 35, 21, 21, 18, 18, 18, 17, 15

    public static List<Integer> getLastFive(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int k : arr) {
            if (map.containsKey(k)) {
                map.put(k, map.get(k) + 1);
            } else {
                map.put(k, 1);
            }
        }

        List<Integer> sortedNumbers = map.keySet()
                .stream()
                .sorted()
                .toList();

        List<Integer> reversedNumbers = new ArrayList<>();

        for (int i = sortedNumbers.size() - 1; i >= 0; i--) {
            reversedNumbers.add(sortedNumbers.get(i));
        }

        List<Integer> resultList = new ArrayList<>();
        int counter = 0;

        for (int i : reversedNumbers) {
            if (counter == 5) {
                break;
            }
            for (int j = 0; j < map.get(i); j++) {
                resultList.add(i);
            }
            counter++;
        }
        return resultList;
    }

    public static String reverseString(char[] word) {
        StringBuilder charToString = new StringBuilder();
        for (char c : word) {
            charToString.append(c);
        }
        return charToString.reverse().toString();
    }

    public static void pyramid() {
        int rows = 7;
        for (int i = 1; i <= rows; i++) {
            for (int j = rows - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void invertedPyramid() {
        int rows = 5;
        for (int i = rows; i >= 1; i--) {
            for (int j = 0; j < rows - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void diamond() {
        int rows = 5;

        for (int i = 1; i <= rows; i++) {

            for (int j = rows - i; j > 0; j--) {
                System.out.print(" ");
            }

            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int i = rows - 1; i >= 1; i--) {
            for (int j = 0; j < rows - i; j++) {
                System.out.print(" ");
            }

            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static int consecutivePairs(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length - 1; i+=2) {
            if(Math.abs(arr[i] - arr[i + 1]) == 1) {
                count++;
            }
        }
        return count;
    }
    public static int consecutivePairsIntStream(int[] arr) {
        return (int) IntStream.iterate(0, i -> i < arr.length - 1, i -> i + 2)
                .filter(i -> Math.abs(arr[i + 1] - arr[i]) == 1).count();
    }
    public static int findMaxIntStream(int[] arr) {
        OptionalInt max = of(arr).max();
        return max.getAsInt();
    }

    public static int maxDiff(int[] lst) {
        return lst.length == 0 ? 0 :
                IntStream.of(lst).max().orElse(0) - IntStream.of(lst).min().orElse(0);
    }

    public static int[] rowWeights (final int[] weights) {

        int firstTeamWeight = 0;
        int secondTeamWeight = 0;

        for (int i = 0; i < weights.length; i++) {
            if(i % 2 != 0) {
                firstTeamWeight += weights[i];
            } else {
                secondTeamWeight += weights[i];
            }
        }

        return new int[]{firstTeamWeight, secondTeamWeight}; // Do your magic!
    }

    public static int[] rotateArray(int[] data, int dist) {
         Collections.rotate(Arrays.asList(data), dist);
         return data;
    }

    public static int[] distinct(int[] array){
        return Arrays.stream(array)
                .distinct()
                .toArray();
    }

    public int removeElement(int[] nums, int val) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[count] = nums[i];
                count++;
            } else {
                return count;
            }
        }
        return count;
    }

    public int singleNumberWithXor(int[] nums) {
        int xor = 0;
        for(int i =0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        return xor;
    }

    public int singleNumberWithStreams(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .mapToInt(Map.Entry::getKey)
                .findAny()
                .orElse(0);
    }
    public int singleNumberWithHashMap(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 0);
            }
            map.put(nums[i], map.get(nums[i]) + 1);
            if(map.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
      if(nums.length == 1) {
          return nums[0];
      }

      Arrays.sort(nums);
      int count = 1;
      int n = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if(n == nums[i]) {
                count++;
            }

            if(count > nums.length / 2) {
                return n;
            }
            else {
                count = nums[i];
                count = 1;
            }
        }
        return 0;
    }

    public static int missingNumber(int[] nums) {

        int sumOfN = IntStream.rangeClosed(0, nums.length).sum();
        int sumOfArr = IntStream.of(nums).sum();

        return sumOfN - sumOfArr;
    }


}
