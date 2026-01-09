package com.group3.digitalWallet.models;
import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private Map<Currency, Double> wallets;

    // esta vacío para Spring
    public User() {
        this.wallets = new HashMap<>();
    }

    public User(int id, String name, Map<Currency, Double> wallets) {
        this.id = id;
        this.name = name;
        this.wallets = wallets;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Map<Currency, Double> getWallets() {
        return wallets;
    }
    public void setWallets(Map<Currency, Double> wallets) {
        this.wallets = wallets;
    }

    public double getBalance(Currency currency) {
        return wallets.get(currency);
    }

    //Returns true if deposit is successful
    public void deposit(double amount, Currency currency){
        wallets.put(currency, wallets.getOrDefault(currency, 0.0) + amount);
    }

    //Returns true if withdraw is successful
    public void withdraw(double amount, Currency currency){
        double balance = wallets.getOrDefault(currency, 0.0);
        wallets.put(currency, balance - amount);
    }
}
