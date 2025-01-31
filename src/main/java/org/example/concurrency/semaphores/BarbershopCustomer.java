package org.example.concurrency.semaphores;

import java.util.Random;
import java.util.logging.Logger;

public class BarbershopCustomer implements Runnable{

    private static final Logger logger =
            Logger.getLogger(BarbershopCustomer.class.getName());
    private static final Random rnd = new Random();
    private final BarberShop barbershop;
    private final int customerId;

    public BarbershopCustomer(BarberShop barbershop, int customerId) {
        this.barbershop = barbershop;
        this.customerId = customerId;
    }

    @Override
    public void run() {

        boolean acquiredSeat = barbershop.acquireSeat(customerId);
        if (acquiredSeat){
            try {
                Thread.sleep(rnd.nextInt(10*1000));
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
                logger.severe(() -> "Exception: " + ex);
            }finally {
                barbershop.releaseSeat(customerId);
            }
        }else {
            Thread.currentThread().interrupt();
        }

    }

    public static void main(String[] args) {
        BarberShop barberShop = new BarberShop(3);
        for (int i = 0; i <=10; i++) {
            BarbershopCustomer barbershopCustomer = new BarbershopCustomer(barberShop,i);
            new Thread(barbershopCustomer).start();
        }
    }
}
