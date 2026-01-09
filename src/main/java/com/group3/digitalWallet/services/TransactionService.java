package com.group3.digitalWallet.services;

import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.Transaction;
import com.group3.digitalWallet.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    private final Map<Integer, Transaction> transactions = new HashMap<>();
    private final CurrencyConversionService currencyConversionService;
    private final UserService userService;
    private int nextId;

    private TransactionService(CurrencyConversionService currencyConversionService, UserService userService) {
        nextId = 1;
        this.currencyConversionService = currencyConversionService;
        this.userService = userService;
    }

    // Returns true if the transaction was successful, false otherwise
    public Transaction makeTransaction(Transaction transaction){
        double amount = transaction.getAmount();
        User origUser = userService.getUserById(transaction.getOriginUserId());
        User destUser = userService.getUserById(transaction.getOriginUserId());
        Currency origCurrency = transaction.getOriginCurrency();
        Currency destCurrency = transaction.getDstCurrency();
        if (origUser == null || destUser == null || amount < 0.0){
            return null;
        }
        if (!userService.withdraw(transaction.getOriginUserId(), origCurrency, amount))
            return null;
        double convertedAmount = currencyConversionService.convert(amount, origCurrency, destCurrency);
        userService.deposit(transaction.getDstUserId(), destCurrency, convertedAmount);
        transaction.setId(nextId);
        transactions.put(nextId, transaction);
        nextId++;
        return transaction;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<Transaction>(transactions.values());
    }

    public Transaction getTransactionById(int id){
        return transactions.get(id);
    }
}
