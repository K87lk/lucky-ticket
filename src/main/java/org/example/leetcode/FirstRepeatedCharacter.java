package org.example.leetcode;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeatedCharacter {
    public static void main(String[] args) {
        String s = "abbcde";
        System.out.println(firstRepeatedCharacter(s));
    }
    public static char firstRepeatedCharacter(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return c;
            } else {
                set.add(c);
            }
        }
        return 0;
    }
}
