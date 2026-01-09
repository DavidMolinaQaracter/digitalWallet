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
    public boolean makeTransaction(Transaction transaction){
        double amount = transaction.getAmount();
        User origUser = UserService.getUserById(transaction.getOriginUserId());
        User destUser = UserService.getUserById(transaction.getOriginUserId());
        Currency origCurrency = transaction.getOriginCurrency();
        Currency destCurrency = transaction.getDstCurrency();
        if (origUser == null || destUser == null || amount < 0.0){
            return false;
        }
        if (!UserService.withdraw(transaction.getOriginUserId(), amount, origCurrency))
            return false;
        double convertedAmount = currencyConversionService.convert(amount, origCurrency, destCurrency);
        UserService.deposit(transaction.getDstUserId(), convertedAmount, destCurrency);
        transactions.put(nextId++, transaction);
        return true;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<Transaction>(transactions.values());
    }

    public Transaction getTransactionById(int id){
        return transactions.get(id);
    }
}
