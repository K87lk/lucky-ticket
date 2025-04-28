package org.example.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Array<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] array;
    private int size;
    private boolean isFixedSize;

    public Array() {
        this.array = new Object[DEFAULT_SIZE];
        this.isFixedSize = false;
    }

    public Array(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be a positive number!");
        }
        this.array = new Object[size];
        this.isFixedSize = true;
    }

    public void add(T object) {
        if (!isFixedSize && size == array.length) {
            int newCapacity = size * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
        if (isFixedSize && size == array.length) {
            throw new IllegalArgumentException("Cannot add elements to a fixed size array!");
        }
        array[size++] = object;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null;
        size--;
    }
    public boolean contains(T object) {
        for (Object o : array) {
            if (o == object) return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.stream(array, 0, size)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
