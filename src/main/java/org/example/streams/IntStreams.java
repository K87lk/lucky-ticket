package org.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class IntStreams {
    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        var sum = numbers.stream().reduce(0, (a, b) -> a + b);
        var sum2 = numbers.stream().reduce(Integer::sum);
        var sum3 = numbers.stream().mapToInt(i -> i).sum();
        var product = numbers.stream().reduce((a, b) -> a * b);

        List<Integer> numbers2 = List.of(3, 5, 1, 10, 7);
        var max = numbers2.stream().reduce(Integer::max);
        var min = numbers2.stream().reduce(Integer::min);
        var average = numbers2.stream().mapToInt(i -> i).average().getAsDouble();


        var sum4 = IntStream.rangeClosed(0, numbers2.size()).sum();
        var sum5 = switch (IntStream.rangeClosed(1, 100).sum()) {
            case 5050 -> "Perfect";
            case 5051 -> "Not perfect";
            default -> throw new IllegalStateException("Unexpected value: " + IntStream.rangeClosed(1, 100).sum());
        };

        var diceRolls = IntStream.generate(() -> (int) (Math.random() * 6) + 1).limit(10).toArray();
        var randomSequence = IntStream.generate(() -> random.nextInt(1000000)).limit(100).boxed().toList();
        System.out.println(randomSequence);

        IntSupplier intSupplier = new IntSupplier() {
            private int current = 0;
            @Override
            public int getAsInt() {
                return current++;
            }
        };

        var incrementalValues = IntStream.generate(intSupplier).limit(10).toArray();
        System.out.println(Arrays.toString(incrementalValues));
    }
}
