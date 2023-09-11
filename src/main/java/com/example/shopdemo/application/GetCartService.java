package com.example.shopdemo.application;

import com.example.shopdemo.dtos.CartDto;
import com.example.shopdemo.models.*;
import com.example.shopdemo.repositories.CartRepository;
import com.example.shopdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public CartDto getCartDto(UserId userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(userId));
        List<CartDto.LineItem> lineItems = lineItemDtos(cart);

        long totalPrice = lineItems.stream().mapToLong(CartDto.LineItem::totalPrice).sum();
        return new CartDto(lineItems, totalPrice);
    }

    private List<CartDto.LineItem> lineItemDtos(Cart cart) {
        return IntStream.range(0, cart.lineItemSize()).mapToObj(index -> {
            CartLineItem lineItem = cart.lineItem(index);
            return lineItemDto(lineItem);
        }).toList();
    }

    private CartDto.LineItem lineItemDto(CartLineItem lineItem) {
        ProductId productId = lineItem.productId();
        Product product = productRepository.findById(productId).orElseThrow();
        Money unitPrice = product.price();
        int quantity = lineItem.quantity();

        return new CartDto.LineItem(lineItem.id().toString(), productDto(product), optionDtos(lineItem, product),
                unitPrice.asLong(), quantity, unitPrice.times(quantity).asLong());
    }

    private CartDto.Product productDto(Product product) {
        return new CartDto.Product(product.id().toString(), new CartDto.Image(product.image(0).url()), product.name());
    }

    private List<CartDto.Option> optionDtos(CartLineItem lineItem, Product product) {
        return lineItem.optionIds().stream().map(optionId -> optionDto(lineItem, product, optionId)).toList();
    }

    private CartDto.Option optionDto(CartLineItem lineItem, Product product, ProductOptionId optionId) {
        ProductOption option = product.optionById(optionId);
        ProductOptionItemId itemId = lineItem.productOptionItemId(optionId);
        ProductOptionItem optionItem = option.itemById(itemId);

        return new CartDto.Option(option.name(), new CartDto.OptionItem(optionItem.name()));
    }
}
