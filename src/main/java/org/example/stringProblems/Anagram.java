package org.example.stringProblems;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    private boolean isAnagram(String a,String b){
        if(a.length() != b.length()) return false;
        Map<Character,Integer> map = new HashMap<>();
        for (char ch : a.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        for (char ch : b.toCharArray()){
            if (!map.containsKey(ch)) return false;
            map.put(ch,map.get(ch)-1);
            if (map.get(ch) ==0) map.remove(ch);
        }
        return true;
    }
    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        System.out.println(anagram.isAnagram("aacc", "ccca"));
//        System.out.println(anagram.isAnagram("listen", "silent"));
    }
}
