package org.example.strings;

import java.util.HashMap;
import java.util.Map;

/*
* Проверьте, можно ли сделать строку палиндромом перестановкой любых символов (игнорируя регистр и пробелы)
* */
public class CanFormPalindrome {
    public static void main(String[] args) {
        System.out.println(canFormPalindrome("race CAR"));
        System.out.println(canFormPalindrome("Abba"));
        System.out.println(canFormPalindrome("Hello"));
    }
    public static boolean canFormPalindrome(String s) {
        s = s.replaceAll("\\s+", "").toLowerCase();
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        int oddCount = 0;
        for (var entry : charCount.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }
}
