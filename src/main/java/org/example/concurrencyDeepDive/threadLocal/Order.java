package org.example.concurrencyDeepDive.threadLocal;

public class Order {

    private final int customerId;

    public Order(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                '}';
    }
}
