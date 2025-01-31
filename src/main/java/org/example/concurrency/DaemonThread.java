package org.example.concurrency;

import java.util.concurrent.CountDownLatch;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {

        int numOfEvents = 3;
        CountDownLatch countDownLatch = new CountDownLatch(numOfEvents);
       Thread damonThread =  new Thread(()->{
           for (int i = 0; i <=numOfEvents; i++) {
               System.out.println("Daemon thread is running...");
               try {
                   Thread.sleep(1000); // Sleep for 1 second
               } catch (InterruptedException e) {
                   System.out.println("Daemon thread interrupted.");
               }finally {
                   countDownLatch.countDown();
               }
           }
        });
        damonThread.setDaemon(true);
        damonThread.start();

       countDownLatch.await();
        System.out.println("Mein thread exit ..");
    }

}
