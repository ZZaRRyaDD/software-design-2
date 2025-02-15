package com.sbertech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GrpcRequestSender {

    @Autowired
    private RequestSender grpcClientService;

    public GrpcRequestSender(RequestSender grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @Scheduled(fixedRate = 5000)
    public void printRate() {
        String currencyFrom = "USD", currencyTo = "RUB";
        try {
            double rate = grpcClientService.getRate(currencyFrom, currencyTo);
            System.out.println("Currency From: " + currencyFrom + ", Currency To: " + currencyTo + ", Result: " + rate);
        } catch (Exception ignored) {
        }
    }
}
