package com.example.shopdemo.controllers;

import com.example.shopdemo.application.CreateOrderService;
import com.example.shopdemo.dtos.OrderRequestDto;
import com.example.shopdemo.models.*;
import com.example.shopdemo.security.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderService createOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(Authentication authentication, @Valid @RequestBody OrderRequestDto orderRequestDto) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId userId = new UserId(authUser.id());

        OrderRequestDto.ReceiverDto receiverDto = orderRequestDto.receiver();
        OrderRequestDto.PaymentDto paymentDto = orderRequestDto.payment();

        Receiver receiver = new Receiver(receiverDto.name(),
                new Address(receiverDto.address1(), receiverDto.address2(), new PostalCode(receiverDto.postalCode())),
                new PhoneNumber(receiverDto.phoneNumber()));
        Payment payment = new Payment(paymentDto.merchantId(), paymentDto.transactionId());

        createOrderService.createOrder(userId, receiver, payment);
        return "Created";
    }

}
