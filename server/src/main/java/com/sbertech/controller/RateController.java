package com.sbertech.controller;

import com.sbertech.model.Rate;
import com.sbertech.service.RateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {
    private final RateProvider rateProvider;

    @Autowired
    public RateController(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    @GetMapping(value = "/rate/{from}/{to}")
    public ResponseEntity<?> getRate(
            @PathVariable(name = "from") String from,
            @PathVariable(name = "to") String to
    ) {
        Rate rate = rateProvider.GetRate(from, to);

        return new ResponseEntity<>(rate, HttpStatus.OK);
    }
}
