package com.sbertech.service;

import com.sbertech.model.Rate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomRateProvider implements RateProvider {
    final double rateMin = 30;
    final double rateMax = 80;

    @Override
    public Rate GetRate(String currencyFrom, String currencyTo) {
        return new Rate(new Random().nextDouble(rateMin, rateMax));
    }
}
