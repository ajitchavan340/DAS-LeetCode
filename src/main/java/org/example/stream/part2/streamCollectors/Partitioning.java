package org.example.stream.part2.streamCollectors;

import org.example.stream.part2.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Partitioning {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static void main(String[] args) {

        Map<Boolean, List<Dish>> collect = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(collect.get(true));

        System.out.println(menu.stream()
                .filter(Dish::isVegetarian).toList());

        System.out.println(menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.groupingBy(Dish::getType))));


    }
}
