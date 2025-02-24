package com.sbertech.request_sender;

import com.sbertech.GetRateResponse;
import com.sbertech.reciever.RateReceiver;
import com.sbertech.reciever.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

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
        LocalTime timeStartExecuteRequest = LocalTime.now();
        Receiver<GetRateResponse> receiver = new RateReceiver(timeStartExecuteRequest);
        try {
            System.out.println("Time start execute request: " + timeStartExecuteRequest + ", Currency From: " + currencyFrom + ", Currency To: " + currencyTo);
            grpcClientService.getRate(receiver, currencyFrom, currencyTo);
        } catch (Exception ignored) {
        }
    }
}
