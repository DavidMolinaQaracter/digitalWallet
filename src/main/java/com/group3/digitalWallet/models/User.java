package com.group3.digitalWallet.models;

import java.util.Map;

public class User {
    int id;
    String name;
    Map<Currency, Double> wallets;

    public User(int id, String name, Map<Currency, Double> wallets) {
        this.id = id;
        this.name = name;
        this.wallets = wallets;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Map<Currency, Double> getWallets() {
        return wallets;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setWallets(Map<Currency, Double> wallets) {
        this.wallets = wallets;
    }
}
