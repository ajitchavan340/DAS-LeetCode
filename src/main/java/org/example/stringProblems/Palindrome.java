package org.example.stringProblems;

public class Palindrome {

    private boolean isPalindrome(String s){
        int left =0,right=s.length()-1;
        while (left<right){
            while (left<right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while (left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            System.out.println("compare left :: " + s.charAt(left)  +" == With right ::"+s.charAt(right));
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(palindrome.isPalindrome("race a car"));

    }
}
