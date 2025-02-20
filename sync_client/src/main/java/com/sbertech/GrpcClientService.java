package com.sbertech;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService implements RequestSender {

    private final ManagedChannel channel;

    public GrpcClientService(@Value("${common.address}") String address, @Value("${common.port}") Integer port) {
        channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
    }

    @Override
    public double getRate(String currencyFrom, String currencyTo) {
        RateServiceGrpc.RateServiceBlockingStub stub = RateServiceGrpc.newBlockingStub(channel);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        GetRateResponse response = stub.getCurrencyRate(GetRate.newBuilder().setCurrencyFrom(currencyFrom).setCurrencyFrom(currencyTo).build());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        return response.getRate();
    }
}
