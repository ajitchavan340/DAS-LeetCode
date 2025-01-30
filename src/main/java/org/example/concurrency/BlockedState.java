package org.example.concurrency;

public class BlockedState {

    private void blockedThread() throws InterruptedException {
        Thread t1 = new Thread(new SyncClass());
        Thread t2 = new Thread(new SyncClass());

        t1.start();
        Thread.sleep(2000);
        t2.start();
        Thread.sleep(2000);

        System.out.println("BlockedThread t1: "
                + t1.getState() + "(" + t1.getName() + ")");
        System.out.println("BlockedThread t2: "
                + t2.getState() + "(" + t2.getName() + ")");
        System.exit(0);

    }

    public static void main(String[] args) throws InterruptedException {
        BlockedState blockedState = new BlockedState();
        blockedState.blockedThread();
    }

    private static class SyncClass implements Runnable{

        @Override
        public  void run() {
            System.out.println("Thread " + Thread.currentThread().getName()
                    + " is in run() method");
            syncMethod();
        }

        private static synchronized void syncMethod() {

            System.out.println("Thread " + Thread.currentThread().getName()
                    + " is in syncMethod() method");
            while (true){

            }
        }
    }
}
