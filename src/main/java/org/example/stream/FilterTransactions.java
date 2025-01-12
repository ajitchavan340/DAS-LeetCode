package org.example.stream;

import org.example.stream.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FilterTransactions {
    List<Transaction> transactionList = List.of(
            new Transaction("HDFC"+ UUID.randomUUID().toString(),1000,"INR", LocalDateTime.now()),
            new Transaction("HDFC"+ UUID.randomUUID().toString(),500,"USD", LocalDateTime.now()),
            new Transaction("HDFC"+ UUID.randomUUID().toString(),100,"INR", LocalDateTime.now()),
            new Transaction("HDFC"+ UUID.randomUUID().toString(),10,"USD", LocalDateTime.now()),
            new Transaction("HDFC"+ UUID.randomUUID().toString(),45.50,"INR", LocalDateTime.now())
    );

}
