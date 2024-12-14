package org.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacter {
    public static void main(String[] args) {
        String s = "loveleetcode";

        System.out.println(firstUniqChar(s));
    }
    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            int freq = map.get(s.charAt(i));
            if(freq == 1) {
                return i;
            }
        }
        return -1;
    }
}
