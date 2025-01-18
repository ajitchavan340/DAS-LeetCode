package org.example.Karplexus;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;

public class Employye{
    private String name;
    private int salary;

    public Employye(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employye{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        List<Employye> list = Arrays.asList(new Employye("Ramesj",10),new Employye("Ajit", 1000), new Employye("Swapnil", 160),new Employye("Bob",500));
        System.out.println(list);
//        Collections.sort(list);
        list.sort(Comparator.comparing(Employye::getName).thenComparing(Employye::getSalary));
        list.sort((e1,e2) -> e1.getName().compareTo(e2.getName()));

       BiFunction<Employye,Employye,Integer> biFunction = (Employye e1, Employye e2) -> e1.getName().compareTo(e2.getName());
       Comparator<Employye> employyeComparator= (Employye e1, Employye e2) -> e1.getName().compareTo(e2.getName());
        list.sort(Comparator.comparing(Employye::getName).reversed());
        System.out.println(list);
    }
}
