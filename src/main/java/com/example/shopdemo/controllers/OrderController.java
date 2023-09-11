package com.example.shopdemo.controllers;

import com.example.shopdemo.application.CreateOrderService;
import com.example.shopdemo.application.GetOrderDetailService;
import com.example.shopdemo.application.GetOrderListService;
import com.example.shopdemo.dtos.OrderDetailDto;
import com.example.shopdemo.dtos.OrderListDto;
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

    private final GetOrderListService getOrderListService;

    private final GetOrderDetailService getOrderDetailService;

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

    @GetMapping
    public OrderListDto list(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId userId = new UserId(authUser.id());

        return getOrderListService.getOrderList(userId);
    }

    @GetMapping("/{id}")
    public OrderDetailDto detail(Authentication authentication, @PathVariable String id) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        OrderId orderId = new OrderId(id);
        UserId userId = new UserId(authUser.id());

        return getOrderDetailService.getOrderDetail(orderId, userId);
    }

}
