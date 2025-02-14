package org.example.concurrencyDeepDive.threadLocal;

import java.util.Random;
import java.util.logging.Logger;

public class CustomerOrder implements Runnable{

    private static final Logger logger = Logger.getLogger(CustomerOrder.class.getName());

    private static final Random random = new Random();
    private static final ThreadLocal<Order> customerOrder = new ThreadLocal<>();

    private final int customerId;

    public CustomerOrder(int customerId) {
        this.customerId = customerId;
    }


    @Override
    public void run() {

        logger.info(() -> "Given customer id: " + customerId
                + " | " + customerOrder.get()
                + " | " + Thread.currentThread().getName());

        customerOrder.set(new Order(customerId));

        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            logger.severe(() -> "Exception: " + ex);
        }
        logger.info(() -> "Given customer id: " + customerId
                + " | " + customerOrder.get()
                + " | " + Thread.currentThread().getName());

        customerOrder.remove();

    }

    public static void main(String[] args) {
        new Thread(new CustomerOrder(1)).start();
        new Thread(new CustomerOrder(2)).start();
        new Thread(new CustomerOrder(3)).start();
    }
}
