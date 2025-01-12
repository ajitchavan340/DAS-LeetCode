package org.example.stream.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String txnId;
    private double txnAmt;
    private String txnCurrency;
    private LocalDateTime txnDateTime;

    public Transaction(String txnId, double txnAmt, String txnCurrency, LocalDateTime txnDateTime) {
        this.txnId = txnId;
        this.txnAmt = txnAmt;
        this.txnCurrency = txnCurrency;
        this.txnDateTime = txnDateTime;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public double getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(double txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTxnCurrency() {
        return txnCurrency;
    }

    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    public LocalDateTime getTxnDateTime() {
        return txnDateTime;
    }

    public void setTxnDateTime(LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txnId='" + txnId + '\'' +
                ", txnAmt=" + txnAmt +
                ", txnCurrency='" + txnCurrency + '\'' +
                ", txnDateTime=" + txnDateTime +
                '}';
    }
}
