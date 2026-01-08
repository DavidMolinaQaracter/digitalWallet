package com.group3.digitalWallet.services;

import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();

    private TransactionService(){
    }

    // Returns true if the transaction was successful, false otherwise
    private boolean makeTransaction(int userId1, Currency user1Currency,
                                    int userId2, Currency user2Currency,
                                    double amount){
        return false; //TODO
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<Transaction>(transactions);
    }

}
