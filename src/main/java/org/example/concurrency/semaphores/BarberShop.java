package org.example.concurrency.semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BarberShop {
    private static final Logger logger = Logger.getLogger(BarberShop.class.getName());
    private final Semaphore seats;
    public BarberShop(int seatCount){
        this.seats = new Semaphore(seatCount,true);
    }

    public boolean acquireSeat(int customerId){
        logger.info(() -> "Customer #" + customerId
                + " is trying to get a seat");
        try {
            boolean acquire = seats.tryAcquire(
                    5 * 1000, TimeUnit.MILLISECONDS
            );
            if (!acquire){
                logger.info(() -> "Customer #" + customerId
                        + " has left the barbershop");
                return false;
            }
            logger.info(() -> "Customer #" + customerId + " got a seat");
            return true;
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
            logger.severe(() -> "Exception: " + ex);
        }
        return false;
    }

    public void releaseSeat(int customerId){
        logger.info(() -> "Customer #" + customerId
                + " has released a seat");
        seats.release();
    }

}
