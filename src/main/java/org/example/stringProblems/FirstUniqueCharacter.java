package org.example.stringProblems;

public class FirstUniqueCharacter {
    private int firstUniqueCharIndex(String s){
        int [] freq = new int[26];

        for (char ch : s.toCharArray()){
            freq[ch -'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if(freq[s.charAt(i)-'a']==1){
                return i;
            }
        }

        return -1;
    }
    public static void main(String[] args) {

        FirstUniqueCharacter firstUniqueCharacter = new FirstUniqueCharacter();
        System.out.println(firstUniqueCharacter.firstUniqueCharIndex("azbcca"));

    }
}
