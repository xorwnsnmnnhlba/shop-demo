package com.example.shopdemo.infra.dtos;

public record PortonePaymentDto(Response response) {

    public record Response(Long amount) {

    }

}
