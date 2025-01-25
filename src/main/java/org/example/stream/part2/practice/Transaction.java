package org.example.stream.part2.practice;

public class Transaction {
    final String txnId;
    final double amount;
    final int year;
    final Trader trader;

    public Transaction(String txnId, double amount, int year, Trader trader) {
        this.txnId = txnId;
        this.amount = amount;
        this.year = year;
        this.trader = trader;
    }

    public String getTxnId() {
        return txnId;
    }



    public double getAmount() {
        return amount;
    }


    public int getYear() {
        return year;
    }

    public Trader getTrader() {
        return trader;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txnId='" + txnId + '\'' +
                ", amount=" + amount +
                ", year=" + year +
                ", trader=" + trader +
                '}';
    }
}
