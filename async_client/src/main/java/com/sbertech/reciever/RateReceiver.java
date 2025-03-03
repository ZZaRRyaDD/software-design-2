package com.sbertech.reciever;

import com.sbertech.models.Rate;

import java.time.LocalTime;

public class RateReceiver implements Receiver<Rate> {

    private final LocalTime timeStartExecuteRequest;

    public RateReceiver(LocalTime timeStartExecuteRequest) {
        this.timeStartExecuteRequest = timeStartExecuteRequest;
    }

    @Override
    public void onObject(Rate rate) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.println("Time start execute request: " + timeStartExecuteRequest + ", current Time: " + LocalTime.now() + ", Rate: " + rate.getRate() + "\n");
    }
}
