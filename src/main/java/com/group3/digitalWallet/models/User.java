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
}