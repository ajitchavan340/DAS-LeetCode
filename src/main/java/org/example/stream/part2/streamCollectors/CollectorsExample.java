package org.example.stream.part2.streamCollectors;

import org.example.stream.part2.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectorsExample {
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
        mostCalorieDish(menu);

        System.out.println("summingInt.."+menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories)));

        System.out.println("averagingInt.."+menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories)));

        System.out.println("joining..."+menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining()));

        System.out.println("joiningWith,..."+menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(",")));

        System.out.println(menu.stream().map(Dish::getCalories).reduce(0, Integer::sum));

        System.out.println(menu.stream()
                .mapToInt(Dish::getCalories).sum());

        System.out.println("groupingBy.."+menu.stream()
                .collect(Collectors.groupingBy(Dish::getType)));

        System.out.println("groupingByCaloricLevel..."+menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));

        System.out.println("groupingByWithFiltering..."+menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.filtering(
                        dish -> dish.getCalories() >= 500, Collectors.toList()
                ))));

        System.out.println("Multilevel grouping..."+menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }))));

        System.out.println(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting())));

        System.out.println(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparing(Dish::getCalories)))));

        System.out.println("collectingAndThen..."+menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                                Optional::get
                        ))));

        System.out.println("groupingByWithSummingInt.."+menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories))));

        System.out.println(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, Collectors.toSet()))));
    }

    private static void mostCalorieDish(List<Dish> menu) {
        Comparator<Dish> dishComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> collect = menu.stream()
                .collect(Collectors.maxBy(dishComparator));
        System.out.println(collect.get());
    }
}
