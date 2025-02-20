package com.sbertech;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.concurrent.Executors;

@Service
public class GrpcRequestSender {

    @Autowired
    private RequestSender grpcClientService;

    public GrpcRequestSender(RequestSender grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

//    @PostConstruct
//    public void stressTask() {
//        Executors.newSingleThreadExecutor().submit(() -> {
//            long startTime = System.currentTimeMillis();
//            int count = 0;
//            while (System.currentTimeMillis() - startTime < 60000) {
//                printRate();
//                count++;
//            }
//            System.out.println(count);
//        });
//    }

    @Scheduled(fixedRate = 5000)
    public void printRate() {
        String currencyFrom = "USD", currencyTo = "RUB";
        try {
            double rate = grpcClientService.getRate(currencyFrom, currencyTo);
            System.out.println("Local Time: " + LocalTime.now() + ", Currency From: " + currencyFrom + ", Currency To: " + currencyTo + ", Result: " + rate);
        } catch (Exception ignored) {
        }
    }
}
