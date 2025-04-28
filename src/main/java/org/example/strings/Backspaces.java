package org.example.strings;

import java.util.Stack;

public class Backspaces {
    public static void main(String[] args) {
        
        /*
        *  На вход приходят строки с символами решетки, которые должны интерпретироваться как backspace.
        * 
        * Требуется реализовать функцию, которая сравнит строки на эквивалентность
        * */
        
        String a = "fo#o#bar#ba##z"; //fbaz
        String b = "foo##bar#z"; //fbaz

        System.out.println(process(a));
        System.out.println(process(b));
        System.out.println(getBackspaceCompare(a, b));
    }

    private static boolean getBackspaceCompare(String a, String b) {
        return process2(a).equals(process2(b));
    }

    private static String process(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
    private static String process2(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
