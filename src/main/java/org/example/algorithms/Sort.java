package org.example.algorithms;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array = {20, 5, 14, 3, 1, 20, -12, 0, -64};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j+ 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
