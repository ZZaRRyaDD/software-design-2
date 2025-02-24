package com.sbertech.observer;

import com.sbertech.GetRateResponse;
import com.sbertech.reciever.Receiver;
import io.grpc.stub.StreamObserver;


public class RateStreamObserver implements StreamObserver<GetRateResponse> {

    private final Receiver<GetRateResponse> rateReceiver;

    public RateStreamObserver(Receiver<GetRateResponse> rateReceiver) {
        this.rateReceiver = rateReceiver;
    }
    @Override
    public void onNext(GetRateResponse rate) {
        rateReceiver.onObject(rate);
    }

    @Override
    public void onError(Throwable cause) {
        System.err.println("getting error" + cause.getMessage());
    }

    @Override
    public void onCompleted() {
    }
}