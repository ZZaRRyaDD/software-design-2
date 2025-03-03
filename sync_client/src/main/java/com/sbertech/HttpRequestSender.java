package com.sbertech;

import com.sbertech.request_sender.RequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class HttpRequestSender {

    @Autowired
    private RequestSender clientService;

    public HttpRequestSender(RequestSender clientService) {
        this.clientService = clientService;
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
            double rate = clientService.getRate(currencyFrom, currencyTo);
            System.out.println("Local Time: " + LocalTime.now() + ", Currency From: " + currencyFrom + ", Currency To: " + currencyTo + ", Result: " + rate);
        } catch (Exception ignored) {
        }
    }
}
