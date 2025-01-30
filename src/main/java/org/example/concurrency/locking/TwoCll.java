package org.example.concurrency.locking;

public class TwoCll {

    public synchronized static void staticMethod1() {
        System.out.println("staticMethod1(): " + Thread.currentThread().getName());
        while (true) {
        }
    }
    
    public synchronized static void staticMethod2() {
        System.out.println("staticMethod2(): " + Thread.currentThread().getName());
        while (true) {
        }
    }

    public static void main(String[] args) {
        new Thread(TwoCll::staticMethod1).start();
        new Thread(TwoCll::staticMethod2).start();
    }
}