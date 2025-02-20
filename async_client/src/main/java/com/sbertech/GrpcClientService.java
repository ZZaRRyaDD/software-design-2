package com.sbertech;

import com.sbertech.observer.RateStreamObserver;
import com.sbertech.reciever.Receiver;
import com.sbertech.request_sender.RequestSender;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService implements RequestSender {

    private final ManagedChannel channel;

    public GrpcClientService(
            @Value("${common.address}") String address,
            @Value("${common.port}") Integer port
    ) {
        channel = ManagedChannelBuilder.
                forAddress(address, port).
                usePlaintext().
                build();
    }

    @Override
    public void getRate(Receiver receiver, String currencyFrom, String currencyTo) {
        RateServiceGrpc.RateServiceStub stub = RateServiceGrpc.newStub(channel);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        StreamObserver<GetRateResponse> observer = new RateStreamObserver(receiver);

        stub.getCurrencyRate(
                GetRate.newBuilder().setCurrencyFrom(currencyFrom).setCurrencyFrom(currencyTo).build(),
                observer
        );
    }
}
