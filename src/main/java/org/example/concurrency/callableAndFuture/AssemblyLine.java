package org.example.concurrency.callableAndFuture;

import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class AssemblyLine {

    private static volatile boolean isProducerRunning;
    private static volatile boolean runningConsumer;
    private static final int MAX_PROD_TIME_MS = 5 * 1000;
    private static final int MAX_CONS_TIME_MS = 3 * 1000;
    private static final int TIMEOUT_MS = MAX_PROD_TIME_MS + MAX_CONS_TIME_MS + 1000;

    private static final Logger logger = Logger.getLogger(AssemblyLine.class.getName());
    private static final Random rnd = new Random();
    private static ExecutorService producerService;
    private static ExecutorService consumerService;

    private static class Producer implements Callable<String>{
        private final String bulb;

        private Producer(String bulb) {
            this.bulb = bulb;
        }

        @Override
        public String call() throws Exception {
            if (isProducerRunning){
                Thread.sleep(MAX_PROD_TIME_MS);

                if (rnd.nextInt()<5){
                    throw new InterruptedException("Defect:" + bulb);
                }else {
                    logger.info(() -> "Checked: " + bulb);
                }
                return bulb;
            }
            return "";
        }
    }

    private static class Consumer implements Runnable {

        private final String bulb;

        private Consumer(String bulb) {
            this.bulb = bulb;
        }

        @Override
        public void run() {
            if (runningConsumer) {
                try {
                    Thread.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                    logger.info(() -> "Packed: " + bulb);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    logger.severe(() -> "Exception: " + ex);
                }
            }
        }
    }

    public static void startAssemblyLine() throws ExecutionException, InterruptedException, TimeoutException {
        if (isProducerRunning) {
            logger.info("Assembly line is already running ...");
            return;
        }

        isProducerRunning = true;
        consumerService = Executors.newSingleThreadExecutor();

        runningConsumer = true;
        producerService = Executors.newSingleThreadExecutor();


        new Thread(AssemblyLine::automaticSystem).start();

        producerService.submit(()-> "");
    }

    private static void automaticSystem() {

        while (isProducerRunning||runningConsumer){
            String bulb = "bulb"+rnd.nextInt(1000);
            Producer producer = new Producer(bulb);
            Future<String> bulbFuture = producerService.submit(producer);

            try {
                String checkedBulb = bulbFuture.get(MAX_PROD_TIME_MS + 1000, TimeUnit.MILLISECONDS);

                Consumer consumer = new Consumer(checkedBulb);

                if (runningConsumer) {
                    consumerService.execute(consumer);
                }
            } catch (ExecutionException ex) {
                logger.severe(() -> "Exception: " + ex.getCause());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                logger.severe(() -> "Exception: " + ex);
            } catch (TimeoutException ex) {
                logger.severe("The producer doesn't respect the checking time!");
            }

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        AssemblyLine.startAssemblyLine();
    }
}
