package org.example.concurrency;

public class TerminatedState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Hello");
        });
        thread.start();
        Thread.sleep(2000);
        System.out.println(thread.getState());
    }
}
