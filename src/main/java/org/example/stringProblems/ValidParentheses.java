package org.example.stringProblems;

import java.util.Stack;

public class ValidParentheses {
    private boolean isValid(String str){
        Stack<Character> characterStack = new Stack<>();
        for (char ch : str.toCharArray()){
            if ('(' == ch || '[' == ch || '{' == ch){
                characterStack.push(ch);
            }else {
                if (characterStack.isEmpty()) return false;
                char top = characterStack.pop();
                if ((ch==')' && top != '(')||
                        (ch==']' && top != '[') ||
                        (ch =='}' && top != '{')) {
                    return false;
                }
            }
        }
        return characterStack.isEmpty();
    }
    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("([])"));
    }
}
