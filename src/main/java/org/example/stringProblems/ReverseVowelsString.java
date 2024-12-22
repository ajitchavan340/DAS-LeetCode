package org.example.stringProblems;

import java.util.Arrays;
import java.util.Set;

public class ReverseVowelsString {

    private void reverseVowelsString(String s){
        Set<Character> vowelsSet = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O','U');
        int left =0,right=s.length()-1;
        //IceCreAm

        char[] charArray = s.toCharArray();
        while (left<right){
            char leftChar = charArray[left];
            char rightChar = charArray[right];
            if (!vowelsSet.contains(leftChar)){
                left++;
                continue;
            }
            if (!vowelsSet.contains(rightChar)){
                right--;
                continue;
            }
                charArray[left] = rightChar;
                charArray[right] = leftChar;
                left++;
                right--;
        }
        System.out.println(new String(charArray));

    }
    public static void main(String[] args) {

        ReverseVowelsString reverseVowelsString = new ReverseVowelsString();
        reverseVowelsString.reverseVowelsString("IceCreAm");
    }
}
