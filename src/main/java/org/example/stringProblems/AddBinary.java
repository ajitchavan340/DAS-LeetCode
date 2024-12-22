package org.example.stringProblems;

public class AddBinary {
    private String addBinary(String a,String b){
        StringBuilder result = new StringBuilder();
        System.out.println(a.length());
         int i = a.length()-1;
        System.out.println(i);
        int j = b.length()-1;
         int carry=0;
         while (i>=0 || j>=0 || carry>0){
             int inta = (i>=0) ? a.charAt(i)-'0':0;
             int intb = (j>=0) ? b.charAt(j)-'0':0;

             int sum = inta + intb + carry;
             result.append(sum%2);
             carry = sum/2;

             i--;
             j--;
         }
         return result.reverse().toString();
    }
    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("1010", "1011"));
    }
}
