package com.example.shopdemo.repositories;

import com.example.shopdemo.models.Order;
import com.example.shopdemo.models.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

}
