package org.example.leetcode;

import java.util.Arrays;

public class ReplaceInPlace {
    public static void main(String[] args) {

        /**
         * Inplace заменяет в массиве символы пробела на последовательность символов '%', '2', '0'.
         *
         * @param str Массив символов с исходной последовательностью и запасом под расширение последовательности.
         * @param len Длина исходной последовательности символов.
         * @return Новая длина последовательности символов.
         */

        char[] src = {'a', 'b', ' ', 'c', ' ', 'd', '_', '_', '_', '_'};
        int newLength = urlify(src, 6);
        char[] dest = Arrays.copyOf(src, newLength);

        System.out.println("New length: " + newLength); // Should print 10
        System.out.println("Result: " + Arrays.toString(dest)); // Should print [a, b, %, 2, 0, c, %, 2, 0, d]

        // Assertions
        assert newLength == 10;
        char[] expected = {'a', 'b', '%', '2', '0', 'c', '%', '2', '0', 'd'};
        assert Arrays.equals(expected, dest);
    }

    public static int urlify(char[] str, int len) {
        int count = 0;
        for (char c : str) {
            if (c == ' ') {
                count++;
            }
        }
        int finalLength = len + (count * 2);
        int index = finalLength - 1;

        for(int i = len - 1; i >= 0; i--) {
            if(str[i] == ' ') {
                str[index] = '0';
                str[index - 1] = '2';
                str[index - 2] = '%';
                index -= 3;
            } else {
                str[index] = str[i];
                index--;
            }
        }
        return finalLength;
    }
}
