package com.group3.digitalWallet.services;

import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.Transaction;
import com.group3.digitalWallet.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();
    private final CurrencyConversionService currencyConversionService;
    private final UserService userService;
    private int nextId;

    private TransactionService(CurrencyConversionService currencyConversionService, UserService userService) {
        nextId = 1;
        this.currencyConversionService = currencyConversionService;
        this.userService = userService;
    }

    // Returns true if the transaction was successful, false otherwise
    public boolean makeTransaction(User origUser, Currency origCurrency,
                                    User destUser, Currency destCurrency,
                                    double amount){
        if (amount < 0.0 || origUser.getBalance(origCurrency) < amount ){
            return false;
        }
        origUser.withdraw(amount, origCurrency);
        double convertedAmount = currencyConversionService.convert(amount, origCurrency, destCurrency);
        destUser.deposit(convertedAmount, destCurrency);
        Transaction transaction = new Transaction(origUser.getId(), amount, origCurrency, destCurrency,
                origUser.getId(), nextId++);
        transactions.add(transaction);
        return true;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<Transaction>(transactions);
    }

}
