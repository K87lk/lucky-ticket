package org.example.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ArrayExercises {
    public static void main(String[] args) {

        int rows = 300;
        int columns = 5;

        int[][] array = new int[rows][columns];

        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = random.nextInt(100);
            }
        }

        String result = Arrays.stream(array)
                .map(row -> Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(", ", "[", "]")))
                .collect(Collectors.joining(", ", "[", "]"));

        List<List<Integer>> resultList = Arrays.stream(array)
                .map(row -> Arrays.stream(row)
                        .boxed()
                        .toList())
                .toList();

        var sum = Arrays.stream(array)
                .flatMap(row -> Arrays.stream(row).boxed())
                .mapToInt(Integer::intValue)
                .sum();

        var sum2 = Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .sum();

        var oddNumbers = Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i % 2 != 0)
                .boxed()
                .toList();


    }
}
