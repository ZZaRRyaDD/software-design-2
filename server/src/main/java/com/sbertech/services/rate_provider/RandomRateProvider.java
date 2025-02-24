package com.sbertech.services.rate_provider;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomRateProvider implements RateProvider {
    final double rateMin = 30;
    final double rateMax = 80;

    @Override
    public double GetRate(String currencyFrom, String currencyTo) {
        return new Random().nextDouble(rateMin, rateMax);
    }
}
