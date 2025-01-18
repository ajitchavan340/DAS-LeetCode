package org.example.stream;

import java.util.function.Function;

public class Letter {

    public static String addHeader(String txt){
        return "From Raoul, Mario and Alan:"+txt;
    }

    public static String addFooter(String txt){
        return txt + " Kind regards";
    }

    public static String checkSpelling(String txt){
        return txt.replaceAll("lamda","lambda");
    }

    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply("Ajit lamda"));
    }
}
