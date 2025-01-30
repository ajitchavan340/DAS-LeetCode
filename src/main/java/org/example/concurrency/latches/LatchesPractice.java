package org.example.concurrency.latches;

import java.util.concurrent.CountDownLatch;

public class LatchesPractice {

    public static void main(String[] args) throws InterruptedException {

        int numOfEvents =3;
        CountDownLatch countDownLatch = new CountDownLatch(numOfEvents);
        for (int i = 1; i <= numOfEvents; i++) {
            final int workId = i;
            new Thread(()->{
                try {
                    System.out.println("Worker " + workId + "started");
                    Thread.sleep(2000);
                    System.out.println("Worker"+workId+ "finished workd");
                }catch (InterruptedException e){
                    System.out.println("Worker " + workId + " was interrupted.");
                }
                finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        System.out.println("Main thread waiting for workers to finish...");
        countDownLatch.await();
        System.out.println("All work are done by workers...");

    }

}
