package org.example.concurrency.Exchnager;

import java.util.concurrent.Exchanger;

public class ExchangeExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            try {
                String producer = "Hey! consumer..";
                producer = exchanger.exchange(producer);
                System.out.println("data from consumer :"+producer);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                String consumer = "Hey! Producer..";
                consumer = exchanger.exchange(consumer);
                System.out.println("data from producer :"+consumer);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }).start();
    }
}
