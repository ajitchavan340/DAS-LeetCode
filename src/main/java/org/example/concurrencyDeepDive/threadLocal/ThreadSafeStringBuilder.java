package org.example.concurrencyDeepDive.threadLocal;

import java.util.Random;
import java.util.logging.Logger;

public class ThreadSafeStringBuilder implements Runnable{

    private static final Logger logger = Logger.getLogger(ThreadSafeStringBuilder.class.getName());
    private static final Random rnd = new Random();

    private static final ThreadLocal<StringBuilder> threadLocal = ThreadLocal.withInitial(()->{
        return new StringBuilder("Thread-safe ");
    });

    @Override
    public void run() {

        logger.info(() -> "-> " + Thread.currentThread().getName()
                + " [" + threadLocal.get() + "]");
        try {
            Thread.sleep(rnd.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadLocal.get().append(Thread.currentThread().getName());
        logger.info(() -> "-> " + Thread.currentThread().getName()
                + " [" + threadLocal.get() + "]");


        threadLocal.remove();


        logger.info(() -> "-> " + Thread.currentThread().getName()
                + " [" + threadLocal.get() + "]");

    }

    public static void main(String[] args) {
        ThreadSafeStringBuilder threadSafeStringBuilder = new ThreadSafeStringBuilder();
        for (int i = 0; i < 3; i++) {
            new Thread(threadSafeStringBuilder,"thread-"+i).start();
        }
    }
}
