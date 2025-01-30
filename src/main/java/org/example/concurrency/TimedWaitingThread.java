package org.example.concurrency;

public class TimedWaitingThread {
    public void timedWaitingThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(500);
        System.out.println("TimedWaitingThread t: "
                + thread.getState());

    }

    public static void main(String[] args) throws InterruptedException {
        TimedWaitingThread timedWaitingThread = new TimedWaitingThread();
        timedWaitingThread.timedWaitingThread();
    }
}
