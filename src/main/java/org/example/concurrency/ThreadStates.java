package org.example.concurrency;

public class ThreadStates {

    public static void main(String[] args) {
        /**
         * NEW
         * RUNNABLE
         * WAITING
         * BLOCKED
         * TIMED_WAITING
         * TERMINATED
         *
         */
        Thread t1 = new Thread(() -> {

        });

        t1.start();

        System.out.println(t1.getState());
    }
}
