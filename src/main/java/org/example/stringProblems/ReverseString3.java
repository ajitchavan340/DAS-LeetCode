package org.example.stringProblems;

public class ReverseString3 {

    public String reverse(String s){
        String[] charArray = s.split(" ");

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            String s1 = charArray[i];
            int left=0,right=s1.length()-1;
            char[] charArray1 = rev(s1.toCharArray(), left, right);
           String sen = new String(charArray1).replaceAll(",", "");
            StringBuilder append = stringBuilder.append(sen);
            if (i < charArray.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private char[] rev(char[] charArray, int left, int right) {
        while (left<right) {
            var temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        return charArray;
    }

    public static void main(String[] args) {
        ReverseString3 reverseString3 = new ReverseString3();
        System.out.println(reverseString3.reverse("Let's take LeetCode contest"));
    }
}
