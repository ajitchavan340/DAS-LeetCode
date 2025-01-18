package org.example.stream;

import org.example.stream.model.Apple;
import org.example.stream.model.AppleFormatter;
import org.example.stream.model.ApplePredicate;

import java.util.*;
import java.util.stream.Collectors;

public class SortApple {
    static List<Apple>  appleList = Arrays.asList(
            new Apple(COLOR.RED,8.9),
            new Apple(COLOR.GREEN,10.1),
            new Apple(COLOR.GREEN,8.9),
            new Apple(COLOR.GREEN,5.9),
            new Apple(COLOR.RED,88.9)
    );

    private List<Apple> filterGreenApples(List<Apple> appleList){
        List<Apple> greenAppleList = new ArrayList<>();
        for (Apple apple : appleList){
            if (apple.getColor() == COLOR.GREEN){
                greenAppleList.add(apple);
            }
        }
        return !greenAppleList.isEmpty() ?greenAppleList:Collections.emptyList();
    }

    private List<Apple> filterApplesByColor(List<Apple> appleList,COLOR color){
        List<Apple> greenAppleList = new ArrayList<>();
        for (Apple apple : appleList){
            if (apple.getColor() == color){
                greenAppleList.add(apple);
            }
        }
        return !greenAppleList.isEmpty() ?greenAppleList:Collections.emptyList();
    }

    private List<Apple> filterHeavyApples(List<Apple> appleList){
        List<Apple> heavyAppleList = new ArrayList<>();
        for (Apple apple : appleList){
            if (apple.getWeight()>10.0){
                heavyAppleList.add(apple);
            }
        }
        return !heavyAppleList.isEmpty() ?heavyAppleList:Collections.emptyList();

    }

    private static List<Apple> filterApples(List<Apple> appleList, ApplePredicate applePredicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : appleList){
            if (applePredicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public void prettyPrintApple(List<Apple> appleList, AppleFormatter appleFormatter){
        for (Apple apple : appleList){
            System.out.println(appleFormatter.accept(apple));
        }
    }

    public static void main(String[] args) {
        SortApple sortApple = new SortApple();
        System.out.println(sortApple.filterGreenApples(appleList));
        System.out.println(sortApple.filterHeavyApples(appleList));
        System.out.println(sortApple.filterApplesByColor(appleList,COLOR.GREEN));

        System.out.println("pass strategy .."+filterApples(appleList, apple -> apple.getWeight() > 8));

        System.out.println("apple list by weight using predicate..."+sortApple.filterApples(appleList, new AppleHeavyWeightPredicate()));

        List<Apple> green = appleList.parallelStream().filter((Apple a) -> a.getColor() == COLOR.GREEN).toList();

        sortApple.prettyPrintApple(appleList,new AppleFancyFormatter());

        System.out.println("Apple list before ..."+appleList);


        appleList.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        System.out.println("Apple list after ..."+appleList);
    }
}
