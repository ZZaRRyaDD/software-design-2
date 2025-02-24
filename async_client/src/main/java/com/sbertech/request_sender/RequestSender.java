package com.sbertech.request_sender;

import com.sbertech.reciever.Receiver;


public interface RequestSender {
    void getRate(Receiver rateReceiver, String currencyFrom, String currencyTo);
}
