package org.example.stringProblems;

import java.util.Arrays;

public class ReverseString {

    private void reverseString(char[] s){
       int left=0,right=s.length-1;

       while (left<right) {
           char temp = s[left];
           s[left] = s[right];
           s[right] = temp;
           left++;
           right--;
       }
        System.out.println(Arrays.toString(s));
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
//        reverseString.reverseString(new char[] {'W','o','r','l','e','y'});
        reverseString.reverseString(new char[] {'h','e','l','l','o'});
    }
}
