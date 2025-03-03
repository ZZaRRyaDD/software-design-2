package com.sbertech.request_sender;

import java.io.IOException;

public interface RequestSender {
    double getRate(String currencyFrom, String currencyTo);
}
