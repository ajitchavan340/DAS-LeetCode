package org.example.stream.anonymous;

import org.example.stream.COLOR;
import org.example.stream.model.Apple;
import org.example.stream.model.ApplePredicate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilterApple {
    static List<Apple>  appleList = List.of(
            new Apple(COLOR.RED,8.9),
            new Apple(COLOR.GREEN,10.1),
            new Apple(COLOR.RED,6.9),
            new Apple(COLOR.GREEN,5.9),
            new Apple(COLOR.RED,88.9)
    );
    private static List<Apple> filterApples(List<Apple> appleList, ApplePredicate applePredicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : appleList){
            if (applePredicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(filterApples(appleList, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 10;
            }
        }));

        List<Apple> apples = filterApples(appleList, (Apple) -> Apple.getColor() == COLOR.GREEN);
        System.out.println(apples);

        Comparator<Apple> appleComparator = Comparator.comparingDouble(Apple::getWeight);
        appleList.sort(appleComparator);
        Comparator<Apple> appleComparator1 = Comparator.comparing(Apple::getColor);

    }
}
