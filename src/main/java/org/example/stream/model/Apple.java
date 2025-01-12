package org.example.stream.model;

import org.example.stream.COLOR;

public class Apple {
    private COLOR color;
    private double weight;

    public Apple(COLOR color,double weight){
        this.color=color;
        this.weight=weight;
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
