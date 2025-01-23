package org.example.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepetition {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of Longest Substring: " + lengthOfLongestSubstring(s)); // Output: 3 ("abc")
    }
    public static int lengthOfLongestSubstring(String s) {

        int maxLength = 0;
        int start = 0;

        Set<Character> seen = new HashSet<>();

        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            while (seen.contains(c)) {
                seen.remove(s.charAt(start));
                start++;
            }
            seen.add(c);
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
