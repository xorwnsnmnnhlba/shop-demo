package com.example.shopdemo.application;

import com.example.shopdemo.dtos.AdminOrderListDto;
import com.example.shopdemo.dtos.AdminOrderSummaryDto;
import com.example.shopdemo.dtos.OrderListDto;
import com.example.shopdemo.dtos.OrderSummaryDto;
import com.example.shopdemo.models.Order;
import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import com.example.shopdemo.repositories.OrderRepository;
import com.example.shopdemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetOrderListService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public OrderListDto getOrderList(UserId userId) {
        List<Order> orders = orderRepository.findAllByUserIdOrderByIdDesc(userId);
        return new OrderListDto(orders.stream().map(OrderSummaryDto::of).toList());
    }

    public AdminOrderListDto getAdminOrderList() {
        List<Order> orders = orderRepository.findAllByOrderByIdDesc();
        List<UserId> userIds = orders.stream().map(Order::userId).toList();
        List<User> users = userRepository.findAllByIdIn(userIds);

        return new AdminOrderListDto(orders.stream().map(order -> {
            User user = users.stream().filter(u -> u.id().equals(order.userId())).findFirst().orElseThrow();
            return AdminOrderSummaryDto.of(order, user);
        }).toList());
    }
}
