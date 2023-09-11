package com.example.shopdemo.repositories;

import com.example.shopdemo.models.Order;
import com.example.shopdemo.models.OrderId;
import com.example.shopdemo.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

    List<Order> findAllByOrderByIdDesc();

    List<Order> findAllByUserIdOrderByIdDesc(UserId userId);

    Optional<Order> findByIdAndUserId(OrderId orderId, UserId userId);

}
