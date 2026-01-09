package com.group3.digitalWallet.controllers;

import com.group3.digitalWallet.models.Transaction;
import com.group3.digitalWallet.models.User;
import com.group3.digitalWallet.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(int id){
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        transactionService.makeTransaction(transaction);
        return transaction;
    }

}
