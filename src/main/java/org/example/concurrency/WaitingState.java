package org.example.concurrency;

public class WaitingState {
    public void waitingState(){
        new Thread(()->{
            Thread t1 = Thread.currentThread();
            Thread t2 = new Thread(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("WaitingThread t1: "
                        + t1.getState());
            });
            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void main(String[] args) {
        WaitingState waitingState = new WaitingState();
        waitingState.waitingState();
    }
}
