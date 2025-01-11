package org.example.stringProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RansomNote {

    private boolean ransomNOte(String first,String two){
        Map<Character,Integer> characterIntegerMap = new HashMap<>();
        for(char ch : two.toCharArray()){
            characterIntegerMap.put(ch,characterIntegerMap.getOrDefault(ch,0)+1);
        }

        for (char ch:first.toCharArray()){
            if (!characterIntegerMap.containsKey(ch) || characterIntegerMap.get(ch) ==0){
                return false;
            }
            characterIntegerMap.put(ch,characterIntegerMap.get(ch)-1);
        }
        return true;
    }
    public static void main(String[] args) {
        RansomNote ransomNote = new RansomNote();
        System.out.println(ransomNote.ransomNOte("aa", "ab"));
    }
}
