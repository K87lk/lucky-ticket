package org.example.strings;

/*
* Given an array of strings words,
* return the words that can be typed using letters of the alphabet
*  on only one row of American keyboard like the image below.
* */

import java.util.*;

public class KeyboardRow {
    public static void main(String[] args) {

        String[] words = {"Hello","Alaska","Dad","Peace"};
        System.out.println(Arrays.toString(findWords(words)));

    }
    public static String[] findWords(String[] words) {
        Set<Character> row1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
        Set<Character> row2 = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'));
        Set<Character> row3 = new HashSet<>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));

        List<String> result = new ArrayList<>();

        for (String word : words) {
            String lowerWord = word.toLowerCase();
            char first = lowerWord.charAt(0);

            Set<Character> targetRow;
            if (row1.contains(first)) {
                targetRow = row1;
            } else if (row2.contains(first)) {
                targetRow = row2;
            } else {
                targetRow = row3;
            }

            boolean valid = true;
            for (char c : lowerWord.toCharArray()) {
                if (!targetRow.contains(c)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                result.add(word);
            }
        }
        return result.toArray(new String[0]);
    }
}
