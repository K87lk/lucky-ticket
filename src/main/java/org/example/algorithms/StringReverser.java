package org.example.algorithms;

import java.util.Stack;

public class StringReverser {
    public static void main(String[] args) {
        System.out.println(reverse3("Hello world!"));
    }
    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    public static String reverse2(String s) {
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        return result;
    }
    public static String reverse3(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.empty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }
}
