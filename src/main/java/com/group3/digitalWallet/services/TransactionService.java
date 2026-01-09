package com.group3.digitalWallet.services;

import com.group3.digitalWallet.models.Currency;
import com.group3.digitalWallet.models.Transaction;
import com.group3.digitalWallet.models.User;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();
    private final CurrencyConversionService currencyConversionService = new CurrencyConversionService();
    private TransactionService(){
    }

    // Returns true if the transaction was successful, false otherwise
    private boolean makeTransaction(User origUser, Currency origCurrency,
                                    User destUser, Currency destCurrency,
                                    double amount){
        if (amount < 0.0 || origUser.getBalance(origCurrency) < amount ){
            return false;
        }
        origUser.withdraw(amount, origCurrency);
        double convertedAmount = currencyConversionService.convert(amount, origCurrency, destCurrency);
        destUser.deposit(convertedAmount, destCurrency);
        return true;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<Transaction>(transactions);
    }

}
