package com.example.shopdemo.application;

import com.example.shopdemo.Fixtures;
import com.example.shopdemo.models.Order;
import com.example.shopdemo.models.OrderId;
import com.example.shopdemo.models.OrderStatus;
import com.example.shopdemo.models.User;
import com.example.shopdemo.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateOrderServiceTest {

    private OrderRepository orderRepository;

    private UpdateOrderService updateOrderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        updateOrderService = new UpdateOrderService(orderRepository);
    }

    @Test
    void updateOrderStatus() {
        User user = Fixtures.user("tester");
        Order order = Fixtures.order(user);

        OrderId orderId = order.id();
        OrderStatus newStatus = OrderStatus.COMPLETE;

        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));
        assertThat(order.status()).isNotEqualTo(newStatus);

        updateOrderService.updateOrderStatus(orderId, newStatus);

        assertThat(order.status()).isEqualTo(newStatus);
    }

}