package org.example.stringProblems;

import java.util.HashMap;
import java.util.Map;

public class FindTheShortestWord {

    private String shortestWord(String licensePlate, String[] words){
        Map<Character,Integer> map = new HashMap<>();
        for(char ch : licensePlate.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                map.put(Character.toLowerCase(ch), map.getOrDefault(Character.toLowerCase(ch), 0) + 1);
            }
        }
        String shortestCompletingWord = null;
        for (String word : words){
            if(isCompletingWord(word,map)){
                if (shortestCompletingWord == null || word.length() < shortestCompletingWord.length()) {
                    shortestCompletingWord = word;
                }
            }
        }
        return shortestCompletingWord;
    }


    boolean isCompletingWord(String word, Map<Character, Integer> targetCounts) {
        Map<Character,Integer> characterIntegerMap = new HashMap<>();
        for (char ch : word.toCharArray()){
            characterIntegerMap.put(ch,characterIntegerMap.getOrDefault(ch,0)+1);
        }

        for (Map.Entry<Character,Integer> entry:targetCounts.entrySet()){
            char ch = entry.getKey();
            int value = entry.getValue();
            if (characterIntegerMap.getOrDefault(ch,0)<value){
                return false;
            }
        }
        return true;

    }


        public static void main(String[] args) {
        FindTheShortestWord findTheShortestWord = new FindTheShortestWord();
//            System.out.println(findTheShortestWord.shortestWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));
            System.out.println(findTheShortestWord.shortestWord("1s3 456", new String[]{"looks","pest","stew","show"}));
        }
}
