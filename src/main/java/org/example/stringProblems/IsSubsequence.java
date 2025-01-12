package org.example.stringProblems;

public class IsSubsequence {

    private boolean subsequence(String s,String t){
       int sIndex=0,tIndex=0;
       while (sIndex<s.length() && tIndex<t.length()){
           if (s.charAt(sIndex) == t.charAt(tIndex)){
               sIndex++;
           }
           tIndex++;
       }
       return sIndex==s.length();
    }
    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        System.out.println(isSubsequence.subsequence("abc", "ahbgdc"));
    }
}
