package org.example.stringProblems;

public class ReverseString2 {

    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int start = 0; start <n ; start+= k*2) {
            int end = (start+k-1 < n ? start+k -1:n-1);
            reverse(arr,start,end);
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int start, int end) {
        while(start<end){
            var temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        ReverseString2 reverseString2 = new ReverseString2();
        System.out.println(reverseString2.reverseStr("abcdefg", 2));
    }
}
