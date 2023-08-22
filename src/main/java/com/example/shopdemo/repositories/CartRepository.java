package com.example.shopdemo.repositories;

import com.example.shopdemo.models.Cart;
import com.example.shopdemo.models.CartId;
import com.example.shopdemo.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    Optional<Cart> findByUserId(UserId userId);

}
