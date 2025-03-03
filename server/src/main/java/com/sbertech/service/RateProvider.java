package com.sbertech.service;

import com.sbertech.model.Rate;

public interface RateProvider {
    Rate GetRate(String currencyFrom, String currencyTo);
}
