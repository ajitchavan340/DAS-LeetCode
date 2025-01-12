package org.example.stream;

import org.example.stream.model.Apple;
import org.example.stream.model.AppleFormatter;

public class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String characteristic =  apple.getWeight()>10 ?"heavy" :"light";
       return  "A " + characteristic +
                " " + apple.getColor() +" apple";
    }
}
