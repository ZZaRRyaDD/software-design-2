package com.sbertech.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rate {
    private final double rate;

    @JsonCreator
    public Rate(@JsonProperty("rate") double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}