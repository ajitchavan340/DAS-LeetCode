package org.example.stream.part2.practice;

import java.util.stream.IntStream;

public class PremitiveStream {
    public static void main(String[] args) {
        System.out.println(IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0).count());
    }
}
