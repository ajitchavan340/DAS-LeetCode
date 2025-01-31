package org.example.concurrency.Exchnager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class AssemblyLine {

    private static final int MAX_PROD_TIME_MS = 2 * 1000;
    private static final Random rnd = new Random();
    private static volatile boolean runningProducer;
    private static final int BUCKET_CAPACITY=5;
    private static final int MAX_CONS_TIME_MS = 5 * 1000;
    private static volatile boolean runningConsumer;
    private static final Exchanger<List<String>> exchanger = new Exchanger<>();
    private static final Logger logger = Logger.getLogger(AssemblyLine.class.getName());
    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();
    private static  ExecutorService producerService;
    private static  ExecutorService consumerService;


    private static class Producer implements Runnable{

        List<String> producerBucket = new ArrayList<>(BUCKET_CAPACITY);

        @Override
        public void run() {
            while (runningProducer) {
                try {
                    for (int i = 0; i <= BUCKET_CAPACITY; i++) {
                        String bulb = "bulb-" + rnd.nextInt(1000);
                        Thread.sleep(MAX_PROD_TIME_MS);
                        producerBucket.add(bulb);
                        logger.info(() -> "Checked and added in the basket: "
                                + bulb);
                    }
                    producerBucket = exchanger.exchange(producerBucket);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    logger.severe(() -> "Exception: " + ex);
                    break;
                }
            }
        }
    }

    private static class Consumer implements Runnable{
        List<String> consumerBucket = new ArrayList<>();

        @Override
        public void run() {
            logger.info("Consumer: Waiting to exchange baskets ...");
            while (runningConsumer){
                try {
                    consumerBucket = exchanger.exchange(consumerBucket);
                    for (String bulb: consumerBucket) {
                        if (bulb != null) {
                            Thread.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                            logger.info(() -> "Packed from basket: " + bulb);
                        }
                    }
                    consumerBucket.clear();
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    logger.severe(() -> "Exception: " + ex);
                    break;
                }
            }
        }
    }

    private static void startAssemblyLine(){

        runningProducer=true;
        producerService = Executors.newSingleThreadExecutor();
        producerService.execute(producer);

        runningConsumer=true;
        consumerService =Executors.newSingleThreadExecutor();
        consumerService.execute(consumer);

    }

    public static void main(String[] args) {
        AssemblyLine.startAssemblyLine();
    }

}
