package org.example.stringProblems;

public class FindCharCountOccurnce {
    private void findCharCount(String s){
        int[] freq = new int[26];
        for (char ch : s.toCharArray()){
            freq[ch -'a']++;
        }
        for (int i = 0; i <s.length() ; i++) {
            System.out.println(s.charAt(i) +" ==" + freq[s.charAt(i)-'a']);
        }
    }
    public static void main(String[] args) {
        FindCharCountOccurnce findCharCountOccurnce = new FindCharCountOccurnce();
        findCharCountOccurnce.findCharCount("awsaadeer");
    }
}
