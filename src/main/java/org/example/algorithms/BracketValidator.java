package org.example.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BracketValidator {

    private static final List<Character> leftBrackets = Arrays.asList('(', '{', '[', '<');
    private static final List<Character> rightBrackets = Arrays.asList(')', '}', ']', '>');

    public static void main(String[] args) {
        String s = "[({[<>]})]";
        System.out.println(validParenthesis(s));
    }
    public static boolean validParenthesis(String s) {
        if (s == null) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (isLeftBracket(c)) {
                stack.push(c);
            }

            if (isRightBracket(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                var top = stack.pop();
                if (!bracketsMatch(top, c)) {
                    return false;
                }
            }
        }
        return stack.empty();
    }
    private static boolean isLeftBracket(char c) {
        return leftBrackets.contains(c);
    }
    private static boolean isRightBracket(char c) {
        return rightBrackets.contains(c);
    }
    private static boolean bracketsMatch(char left, char right) {
        return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
    }
}
