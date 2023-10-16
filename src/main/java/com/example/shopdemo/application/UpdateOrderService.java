package com.example.shopdemo.application;

import com.example.shopdemo.models.Order;
import com.example.shopdemo.models.OrderId;
import com.example.shopdemo.models.OrderStatus;
import com.example.shopdemo.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateOrderService {

    private final OrderRepository orderRepository;

    public void updateOrderStatus(OrderId orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.changeStatus(status);
    }
}
