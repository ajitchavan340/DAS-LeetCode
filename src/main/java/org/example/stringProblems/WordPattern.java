package org.example.stringProblems;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    private boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false; // If lengths don't match, it's not a valid pattern
        }

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            // Check if the character is already mapped
            if (charToWord.containsKey(ch)) {
                if (!charToWord.get(ch).equals(word)) {
                    return false; // Mismatch in mapping
                }
            } else {
                charToWord.put(ch, word);
            }

            // Check if the word is already mapped
            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != ch) {
                    return false; // Mismatch in mapping
                }
            } else {
                wordToChar.put(word, ch);
            }
        }

        return true; // All mappings are consistent
    }

    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        System.out.println(wordPattern.wordPattern("abba", "dog cat cat dog"));
//        System.out.println(wordPattern.wordPattern("aaaa", "dog cat cat dog"));
    }
}
