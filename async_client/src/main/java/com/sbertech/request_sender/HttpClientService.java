package com.sbertech.request_sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbertech.models.Rate;
import com.sbertech.reciever.Receiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Service
public class HttpClientService implements RequestSender {

    private final HttpClient client;
    private final HttpRequest request;

    public HttpClientService(@Value("${common.address}") String address, @Value("${common.port}") Integer port) {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + address + ":" + port + "/rate/USD/RUB"))
                .build();
    }

    @Override
    public void getRate(Receiver rateReceiver, String currencyFrom, String currencyTo) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenApply(HttpResponse::body)
            .thenApply(body -> {
                try {
                    return new ObjectMapper().readValue(body, Rate.class);
                } catch (JsonProcessingException e) {
                    System.err.println(e);
                    throw new RuntimeException(e);
                }
            }).thenAccept(rate -> rateReceiver.onObject(rate));
    }
}
