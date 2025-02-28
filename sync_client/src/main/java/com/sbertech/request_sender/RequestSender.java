package com.sbertech;

import java.io.IOException;

public interface RequestSender {
    double getRate(String currencyFrom, String currencyTo) throws IOException, InterruptedException;
}
