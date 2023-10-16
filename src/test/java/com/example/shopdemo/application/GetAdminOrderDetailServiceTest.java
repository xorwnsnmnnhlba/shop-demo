package com.example.shopdemo.application;

import com.example.shopdemo.Fixtures;
import com.example.shopdemo.dtos.AdminOrderDetailDto;
import com.example.shopdemo.models.Order;
import com.example.shopdemo.models.User;
import com.example.shopdemo.repositories.OrderRepository;
import com.example.shopdemo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetAdminOrderDetailServiceTest {

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private GetAdminOrderDetailService getAdminOrderDetailService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        userRepository = mock(UserRepository.class);
        getAdminOrderDetailService = new GetAdminOrderDetailService(orderRepository, userRepository);
    }

    @Test
    void getOrderDetail() {
        User user = Fixtures.user("tester");
        Order order = Fixtures.order(user);

        given(orderRepository.findById(order.id())).willReturn(Optional.of(order));
        given(userRepository.findById(user.id())).willReturn(Optional.of(user));

        AdminOrderDetailDto orderDetailDto = getAdminOrderDetailService.getOrderDetail(order.id());
        assertThat(order.lineItems()).hasSize(1);
    }

}
