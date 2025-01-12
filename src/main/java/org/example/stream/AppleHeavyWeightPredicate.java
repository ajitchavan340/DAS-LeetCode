package org.example.stream;

import org.example.stream.model.Apple;
import org.example.stream.model.ApplePredicate;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 15;
    }
}
