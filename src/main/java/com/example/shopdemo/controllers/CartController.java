package com.example.shopdemo.controllers;

import com.example.shopdemo.application.GetCartService;
import com.example.shopdemo.dtos.CartDto;
import com.example.shopdemo.models.UserId;
import com.example.shopdemo.security.AuthUser;
import com.example.shopdemo.security.UserRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final GetCartService getCartService;

    @GetMapping
    public CartDto detail(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId userId = new UserId(authUser.id());
        return getCartService.getCartDto(userId);
    }

}
