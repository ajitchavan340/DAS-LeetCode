package org.example.stream.model;

import org.example.stream.COLOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateApple {

    static List<Apple> appleList = Arrays.asList(
            new Apple(COLOR.RED,8.9),
            new Apple(COLOR.GREEN,10.1),
            new Apple(COLOR.GREEN,8.9),
            new Apple(COLOR.GREEN,5.9),
            new Apple(COLOR.RED,88.9)
    );


    public static void main(String[] args) {
        Predicate<Apple> applePredicate = (apple) -> "RED".equals(apple.getColor());

        System.out.println(applePredicate.and((apple) -> apple.getWeight() > 150));
    }

}
