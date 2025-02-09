package com.sbertech.services.rate_provider;

public interface RateProvider {
    double GetRate(String currencyFrom, String currencyTo);
}
