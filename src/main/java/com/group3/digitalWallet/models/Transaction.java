package com.group3.digitalWallet.models;

public class Transaction {
    int id;
    int originUserId, dstUserId;
    double amount;
    Currency originCurrency, dstCurrency;

    public Transaction(int dstUserId, double amount, Currency originCurrency, Currency dstCurrency, int originUserId, int id) {
        this.dstUserId = dstUserId;
        this.amount = amount;
        this.originCurrency = originCurrency;
        this.dstCurrency = dstCurrency;
        this.originUserId = originUserId;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(int originUserId) {
        this.originUserId = originUserId;
    }

    public int getDstUserId() {
        return dstUserId;
    }

    public void setDstUserId(int dstUserId) {
        this.dstUserId = dstUserId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getOriginCurrency() {
        return originCurrency;
    }

    public void setOriginCurrency(Currency originCurrency) {
        this.originCurrency = originCurrency;
    }

    public Currency getDstCurrency() {
        return dstCurrency;
    }

    public void setDstCurrency(Currency dstCurrency) {
        this.dstCurrency = dstCurrency;
    }
}
