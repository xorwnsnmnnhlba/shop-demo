package com.example.shopdemo.controllers;

import com.example.shopdemo.application.AddProductToCartService;
import com.example.shopdemo.dtos.AddProductToCartDto;
import com.example.shopdemo.models.*;
import com.example.shopdemo.security.AuthUser;
import com.example.shopdemo.security.UserRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@UserRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart/line-items")
public class CartLineItemController {

    private final AddProductToCartService addProductToCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(Authentication authentication, @Valid @RequestBody AddProductToCartDto requestDto) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId userId = new UserId(authUser.id());
        ProductId productId = new ProductId(requestDto.productId());
        Set<CartLineItemOption> options = requestDto.options().stream().map(option ->
                        new CartLineItemOption(new ProductOptionId(option.id()), new ProductOptionItemId(option.itemId())))
                .collect(Collectors.toSet());
        int quantity = requestDto.quantity();

        addProductToCartService.addProductToCart(userId, productId, options, quantity);

        return "Created";
    }

}
