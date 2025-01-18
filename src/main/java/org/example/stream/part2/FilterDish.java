package org.example.stream.part2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilterDish {

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


    private List<String> filterDishByLowCaloricDishesName(List<Dish> dishList){
        return dishList.parallelStream()
                .filter(d -> d.getCalories()<300)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .toList();
    }

    private List<String> threeHighCaloricDishNames(List<Dish> dishList){
        return dishList.stream()
                .filter(d ->{
                    System.out.println("filtering:" + d.getName());
                   return d.getCalories()>300;
                })
                .map(dish -> {
                    System.out.println("mapping:" +dish.getName());
                    return dish.getName();
                })
                .limit(3).toList();
    }

    public static void main(String[] args) {
        FilterDish filterDish = new FilterDish();
        long start = System.currentTimeMillis();
//        System.out.println(filterDish.filterDishByLowCaloricDishesName(menu));
        System.out.println(filterDish.threeHighCaloricDishNames(menu));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
