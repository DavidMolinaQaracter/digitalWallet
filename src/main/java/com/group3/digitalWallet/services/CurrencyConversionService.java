package com.group3.digitalWallet.services;

import com.group3.digitalWallet.models.Currency;

public class CurrencyConversionService {

    // DEFINIMOS EL VALOR DE 1 EURO EN LAS OTRAS MONEDAS
    // Base: 1 EUR = 1.0 EUR
    private final double RATE_EUR = 1.0;

    // 1 EUR = 1.09 USD (aprox)
    private final double RATE_USD = 1.09;

    // 1 EUR = 0.85 GBP (aprox)
    private final double RATE_GBP = 0.85;

    /**
     * Convert the currency From To
     */
    public double convert(double amount, Currency from, Currency to) {
        if (from == to) {
            return amount;
        }

        double fromRate = getRateRelativetoEuro(from);
        double toRate = getRateRelativetoEuro(to);

        return amount * (toRate / fromRate);
    }

    private double getRateRelativetoEuro(Currency currency) {
        return switch (currency) {
            case USD -> RATE_USD;
            case GBP -> RATE_GBP;
            case EUR -> RATE_EUR;
            default -> 1.0;
        };
    }
}
