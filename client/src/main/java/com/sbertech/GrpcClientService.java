package com.sbertech;

import com.sbertech.GetRateResponse;
import com.sbertech.GetRate;
import com.sbertech.RateServiceGrpc;
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

    public double getRate(String currencyFrom, String currencyTo) {
        RateServiceGrpc.RateServiceBlockingStub stub = RateServiceGrpc.newBlockingStub(channel);

        GetRateResponse response = stub.getCurrencyRate(GetRate.newBuilder().setCurrencyFrom(currencyFrom).setCurrencyFrom(currencyTo).build());

        return response.getRate();
    }
}
