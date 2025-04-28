package org.example.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayExercises2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int rows = 30000;
        int columns = 5;

        int[][] array = new int[rows][columns];

        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = random.nextInt(100);
            }
        }

        List<Integer> allNumbers = Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .toList();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<List<Integer>> evenFuture = CompletableFuture.supplyAsync(() -> allNumbers.stream()
                        .filter(i -> i % 2 == 0)
                        .toList(), executorService);

        CompletableFuture<List<Integer>> oddFuture = CompletableFuture.supplyAsync(() -> allNumbers.stream()
                .filter(i -> i % 2 != 0)
                .toList(), executorService);

        executorService.shutdown();

    }
}
