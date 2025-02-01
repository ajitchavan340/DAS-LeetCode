package org.example.concurrencyDeepDive.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExampleOfThenCompose {

    public static CompletableFuture<String> fetchOrder(int customerId){
        return CompletableFuture.supplyAsync(()-> {
                return "orderOf"+customerId;
            });
    }

    public static CompletableFuture<Integer> computeTotal(String order){
        return CompletableFuture.supplyAsync(() ->{
           return  order.length()+new Random(1000).nextInt();
        });
    }

    private static CompletableFuture<String> packProducts(String order) {
        return CompletableFuture.supplyAsync(() -> {
            return "Order: " + order
                    + " | Product 1, Product 2, Product 3, ... ";
        });
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = fetchOrder(789654).thenCompose(ExampleOfThenCompose::computeTotal);
        System.out.println(integerCompletableFuture.get());

        CompletableFuture<String> completableFuture = computeTotal("bulb").
                thenCombine(packProducts("bulb"), (total, products) -> {
            return "Parcel-[" + products + " Invoice: $" + total + "]";
        });
        String string = completableFuture.get();

        System.out.println(string);


    }

}
