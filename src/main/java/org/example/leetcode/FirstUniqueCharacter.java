package org.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacter {
    public static void main(String[] args) {
        String s = "loveleetcode";

        System.out.println(firstUniqChar(s));
    }
    public static char firstUniqChar(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        var chars = s.toCharArray();
        for (char c: chars) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        for (char c: chars) {
            if (charMap.get(c) == 1) {
                return c;
            }
        }
        return 0;
    }
}
