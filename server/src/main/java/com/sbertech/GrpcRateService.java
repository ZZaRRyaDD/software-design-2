package com.sbertech;

import com.sbertech.services.rate_provider.RateProvider;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GrpcRateService extends RateServiceGrpc.RateServiceImplBase {

    private final com.sbertech.services.rate_provider.RateProvider rateProvider;

    @Autowired
    public GrpcRateService(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    @Override
    public void getCurrencyRate(
        GetRate request,
        io.grpc.stub.StreamObserver<GetRateResponse> responseObserver
    ) {
        double rate = rateProvider.GetRate(request.getCurrencyFrom(), request.getCurrencyTo());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        GetRateResponse.Builder response = GetRateResponse.newBuilder().setRate(rate);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
