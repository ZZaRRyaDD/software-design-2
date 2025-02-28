package com.sbertech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbertech.request_sender.RequestSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
    public double getRate(String currencyFrom, String currencyTo) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Rate rate = null;
        try {
            rate = objectMapper.readValue(response.body(), Rate.class);
        } catch (JsonProcessingException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return rate.getRate();
    }
}
