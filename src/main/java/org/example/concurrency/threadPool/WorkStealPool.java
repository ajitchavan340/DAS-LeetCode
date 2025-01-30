package org.example.concurrency.threadPool;

import org.example.concurrency.WaitingState;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class WorkStealPool {

    private static final Logger logger = Logger.getLogger(WorkStealPool.class.getName());
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static final int PROCESSOR = Runtime.getRuntime().availableProcessors()*2+1;
    private static final Consumer consumer = new Consumer();
    private static final int MAX_PROD_BULBS = 15_000_000;

    private static final Random rnd = new Random();

    private static long startTime;
    private static ExecutorService consumerService;

    public static class Consumer implements Runnable{

        @Override
        public void run() {
            String bulb = queue.poll();
            if (bulb!=null){

            }
            if (queue.isEmpty()) {
                logger.info(() -> "The consumers team packed all bulbs in "
                        + (System.currentTimeMillis() - startTime) + " ms");
                logger.info("Note: It is possible to see the above message multiple times...");

                System.exit(0);
            }
        }
    }

    public static void startAssemblyLine() {
        simulatingProducers();
        startConsumers();
    }

    private static void startConsumers() {
        logger.info(() -> "We have a consumers team of "
                + PROCESSOR + " members ...");

        consumerService = Executors.newWorkStealingPool(PROCESSOR);

        int queueSize = queue.size();

        startTime = System.currentTimeMillis();
        for (int i = 0; i < queueSize; i++) {
            consumerService.execute(consumer);
        }

        consumerService.shutdown();
    }

    private static void simulatingProducers() {
        logger.info("Simulating the job of the producers overnight ...");
        logger.info(() -> "The producers checked " + MAX_PROD_BULBS + " bulbs ...");

        for (int i = 0; i < MAX_PROD_BULBS; i++) {
            queue.offer("bulb-" + rnd.nextInt(1000));
        }
    }

    public static void main(String[] args) {
        WorkStealPool.startAssemblyLine();
    }
}
