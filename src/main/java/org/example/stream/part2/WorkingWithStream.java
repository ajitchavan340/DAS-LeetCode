package org.example.stream.part2;

import java.util.Arrays;
import java.util.List;

public class WorkingWithStream {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4,5,7,8,9);

        List<String> words = Arrays.asList("Ajit","Ananya","Amruta");

        String [] word = {"goodBy","world"};

        int [] ints = {1,2,3,4,5};

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        numbers.stream()
                .distinct()
                .filter(n -> n%2==0)
                .forEach(System.out::println);

        numbers.stream()
                .takeWhile(n -> n<5)
                .forEach(System.out::println);

        numbers.stream()
                .dropWhile(n -> n<5)
                .forEach(System.out::println);

        words.stream()
                .map(String::length)
                .toList().forEach(System.out::println);

        Arrays.stream(word)
                .map(m ->m.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList().forEach(System.out::println);


        Arrays.stream(ints)
                .map(n-> n*n)
                .forEach(System.out::print);

        List<int[]> list = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i,j}))
                .toList();

        list.forEach(pair -> System.out.println(Arrays.toString(pair)));


       numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[] {i,j}))
                .toList().forEach(pair -> System.out.println(Arrays.toString(pair)));


        System.out.println(numbers1.stream()
                .reduce(0, Integer::sum)
                .intValue());

    }

}
