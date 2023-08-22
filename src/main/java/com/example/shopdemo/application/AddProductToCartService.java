package com.example.shopdemo.application;

import com.example.shopdemo.models.*;
import com.example.shopdemo.repositories.CartRepository;
import com.example.shopdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AddProductToCartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public void addProductToCart(UserId userId, ProductId productId, Set<CartLineItemOption> options, int quantity) {
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(CartId.generate(), userId));
        Product product = productRepository.findById(productId).orElseThrow();
        cart.addProduct(product, options, quantity);
        cartRepository.save(cart);
    }
}
