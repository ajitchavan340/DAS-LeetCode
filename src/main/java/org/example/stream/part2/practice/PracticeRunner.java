package org.example.stream.part2.practice;

import java.util.*;
import java.util.stream.Stream;

public class PracticeRunner {

    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario","Milan");
    static Trader alan = new Trader("Alan","Cambridge");
    static Trader brian = new Trader("Brian","Cambridge");
    static List<Transaction> transactionList = Arrays.asList(
            new Transaction("AJIT"+ UUID.randomUUID(),500,2010,raoul),
            new Transaction("AJIT"+ UUID.randomUUID(),800,2011,mario),
            new Transaction("AJIT"+ UUID.randomUUID(),50,2019,alan),
            new Transaction("AJIT"+ UUID.randomUUID(),900,2011,brian),
            new Transaction("AJIT"+ UUID.randomUUID(),500,2011,raoul),
            new Transaction("AJIT"+ UUID.randomUUID(),4.00,2014,brian),
            new Transaction("AJIT"+ UUID.randomUUID(),500,2010,raoul)
    );


    public List<Transaction> fetchAllTransaction (List<Transaction> transactionList){
        return transactionList.stream()
                .filter(transaction -> transaction.year==2011)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .toList();
    }

    public List<String> uniqueCitiesOfTraders(List<Transaction> traderList){
        return traderList.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList();
    }

    public List<Transaction> findTheTradersFromCambridge(List<Transaction> transactionList){
        return transactionList.stream()
                .filter(transaction -> transaction.trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(transaction -> transaction.trader.getName()))
                .toList();
    }

    public String tradersNameList(List<Transaction> transactionList){
        return transactionList.stream()
                .map(transaction -> transaction.trader.getName())
                .distinct()
                .sorted(Comparator.comparing(String::toString))
                .reduce("",(s1,s2)->s1+s2);
    }

    public boolean areAnyTradersBasedInMilan(List<Transaction> transactionList){
        return transactionList.stream()
                .anyMatch(transaction -> transaction.trader.getCity().equals("Milan"));
    }

    public List<Double>  transactionsFromTradersLivingInCambridge(List<Transaction> transactionList){
        return transactionList.stream()
                .filter(transaction -> transaction.trader.getCity().equals("Cambridge"))
                .map(transaction -> transaction.amount)
                .toList();
    }

    public Optional<Double> highestValueTransactions(List<Transaction> transactionList){

//        return transactionList.stream()
//                .map(transaction -> transaction.amount)
//                .max(Comparator.comparing(Double::doubleValue));

        return transactionList.stream()
                .map(Transaction::getAmount)
                .reduce(Double::max);
    }

    public Optional<Double> smallestValueTransactions(List<Transaction> transactionList){

//        return transactionList.stream()
//                .map(transaction -> transaction.amount)
//                .max(Comparator.comparing(Double::doubleValue));

//        return transactionList.stream()
//                .map(Transaction::getAmount)
//                .reduce(Double::min);

        return transactionList.stream()
                .map(Transaction::getAmount)
                .reduce((t1,t2) -> t1<t2 ? t1:t2);
    }

    public static void main(String[] args) {

        PracticeRunner practiceRunner = new PracticeRunner();
        System.out.println(practiceRunner.fetchAllTransaction(transactionList));
        System.out.println(practiceRunner.uniqueCitiesOfTraders(transactionList));
        System.out.println(practiceRunner.findTheTradersFromCambridge(transactionList));
        System.out.println(practiceRunner.tradersNameList(transactionList));
        System.out.println(practiceRunner.areAnyTradersBasedInMilan(transactionList));
        System.out.println(practiceRunner.transactionsFromTradersLivingInCambridge(transactionList));
        System.out.println(practiceRunner.highestValueTransactions(transactionList));
        System.out.println(practiceRunner.smallestValueTransactions(transactionList));

        String sysValue = System.getProperty("abc");
        Stream<String> v = sysValue == null ? Stream.empty() : Stream.of(sysValue);
        System.out.println(v);

        System.out.println(Stream.ofNullable(System.getProperty("abc")));

        Stream.iterate(0,n->n+2)
                .limit(10)
                .forEach(System.out::println);
    }
}
