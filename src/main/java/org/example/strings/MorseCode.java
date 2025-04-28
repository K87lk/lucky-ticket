package org.example.strings;

import java.util.HashSet;
import java.util.Set;

public class MorseCode {
    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(uniqueMorseRepresentations(words));
    }
    public static int uniqueMorseRepresentations(String[] words) {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",
                ".---","-.-",".-..","--","-.","---",".--.","--.-",
                ".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        Set<String> transformations = new HashSet<>();
        for (String word : words) {
            StringBuilder morseString = new StringBuilder();
            for (char c : word.toCharArray()) {
                morseString.append(morse[c - 'a']);
            }
            transformations.add(morseString.toString());
        }
        return transformations.size();
    }
}
