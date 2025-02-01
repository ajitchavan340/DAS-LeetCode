package org.example.concurrencyDeepDive.CompletableFuture;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class VoidCompletableFeature {

    public static void fetchInvoiceTotalSign() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Invoice #3344";
                }).thenApply(o -> "Total: $145")
                    .thenApply(o -> "signed ");

        String string = completableFuture.get();

        System.out.println(string);
    }

    private static void fetchAndPrintOrder(){
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "Order #1024";
        }).thenAccept(o -> {
            System.out.println("Oreder"+o);
        });
    }

    private static void fetchOrderTotalException() {
        CompletableFuture.supplyAsync(() -> {
            int surrogate = new Random().nextInt(1000);
            if (surrogate < 500) {
                throw new IllegalStateException(
                        "Invoice service is not responding");
            }
            return 1000;
        }).thenAccept((o)->
                System.out.println("HEllo"))
                .exceptionally(ex -> {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        });
    }

    private static void exceptionllycompose(){
        CompletableFuture<String> cfServicePrinterIp
                = CompletableFuture.supplyAsync(() -> {
            int surrogate = new Random().nextInt(1000);
            if (surrogate < 500) {
                throw new IllegalStateException(
                        "Printing service is not responding");
            }
            return "192.168.1.0";
        });


        CompletableFuture<String> cfBackupPrinterIp
                = CompletableFuture.supplyAsync(() -> {
            return "192.192.192.192";
        });

        cfServicePrinterIp.exceptionallyCompose((ex) ->{
            return cfBackupPrinterIp;
        }).thenAccept(System.out::println);

    }

    private static CompletableFuture<Integer> taxes(){
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            int result = new Random().nextInt();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                completableFuture.complete(result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return completableFuture;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() ->
                System.out.println("Order printed by " + Thread.currentThread().getName()));
        voidCompletableFuture.get();


       CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
                return "Order process by #78954";
               });

        String order = completableFuture.get();
        System.out.println(order);

       // fetchInvoiceTotalSign();
        //fetchAndPrintOrder();
        //fetchOrderTotalException();
//        exceptionllycompose();

        CompletableFuture<Integer> taxes = taxes();
        while (!taxes.isDone()){
            System.out.println("Still in_progress");
        }
        Integer integer = taxes.get();
        System.out.println(integer);
    }


}
