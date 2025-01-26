package org.example.stream.part2.practice;

import java.util.stream.Stream;

public class Fibonacci {
    public static void main(String[] args) {
       int count = 10;
        int a=0,b=1,c;
        for (int i=0;i<=count;i++){
            c = a+b;
            System.out.println(" "+ c);
            a = b;
            b=c;
        }

        Stream.iterate(new int[]{0,1},
                t -> new int[]{t[1],t[0]+t[1]})
                .limit(10)
                .forEach(t ->System.out.println(("("+t[0])+ "," + t[1] +")"));
    }
}
