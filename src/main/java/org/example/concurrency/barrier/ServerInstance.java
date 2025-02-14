package org.example.concurrency.barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerInstance implements Runnable{

    private static final Logger logger = Logger.getLogger(ServerInstance.class.getName());

    private final Runnable barrier = ()-> logger.info("Services are ready to start ...");

    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(3,barrier);

    @Override
    public void run() {

        logger.info("The server is getting ready to start ");
        logger.info("Starting services ...\n");

        long starting = System.currentTimeMillis();

        Thread service1 = new Thread(new ServerService(cyclicBarrier,"HTTP Listeners"));
        Thread service2 = new Thread(new ServerService(cyclicBarrier,"JMX"));
        Thread service3 = new Thread(new ServerService(cyclicBarrier,"Connectors"));

        service1.start();
        service2.start();
        service3.start();

        try {
            service1.join();
            service2.join();
            service3.join();

            logger.info(() -> "Server has successfully started in "
                    + (System.currentTimeMillis() - starting) / 1000 + " seconds");
        }catch (InterruptedException e){
            Logger.getLogger(ServerInstance.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        Thread server = new Thread(new ServerInstance());
        server.start();
    }
}
