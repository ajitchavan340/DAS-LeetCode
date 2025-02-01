package org.example.concurrencyDeepDive.atomicVariables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Incrementator implements Runnable{
    private static AtomicInteger count =new AtomicInteger();
    @Override
    public void run() {
        count.getAndIncrement();
    }

    public int getCount(){
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Incrementator incrementator = new Incrementator();
        
        ExecutorService countIncrementService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 1_000_000; i++) {
            countIncrementService.execute(incrementator);
        }

        countIncrementService.shutdown();
        countIncrementService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println(incrementator.getCount());
    }
}
