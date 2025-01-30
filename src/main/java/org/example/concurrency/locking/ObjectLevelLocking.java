package org.example.concurrency.locking;

public class ObjectLevelLocking {

        public synchronized  void method1(){
            System.out.println("method1.{}"+Thread.currentThread().getName());
            while (true){

            }
        }

        public synchronized void method2(){
            System.out.println("method2"+Thread.currentThread().getName());

            while (true){

            }
        }


    public static void main(String[] args) throws InterruptedException {
            ObjectLevelLocking objectLevelLocking = new ObjectLevelLocking();
            new Thread(objectLevelLocking::method1).start();
            new Thread(objectLevelLocking::method2).start();

    }

}
