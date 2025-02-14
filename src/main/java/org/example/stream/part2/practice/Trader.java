package org.example.stream.part2.practice;

import java.io.Serializable;

public class Trader implements Serializable {
 private final String name;
 private final String city;
 public Trader(String n, String c){
 this.name = n;
 this.city = c;
 }
 public String getName(){
 return this.name;
 }
 public String getCity(){
 return this.city;
 }
 public String toString(){
 return "Trader:"+this.name + " in " + this.city;
 }
}