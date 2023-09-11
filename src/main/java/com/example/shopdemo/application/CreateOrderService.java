package com.example.shopdemo.application;

import com.example.shopdemo.infra.PaymentValidator;
import com.example.shopdemo.models.*;
import com.example.shopdemo.repositories.CartRepository;
import com.example.shopdemo.repositories.OrderRepository;
import com.example.shopdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;


@Service
@Transactional
@RequiredArgsConstructor
public class CreateOrderService {

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final OrderRepository orderRepository;

    private final PaymentValidator paymentValidator;

    public Order createOrder(UserId userId, Receiver receiver, Payment payment) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        List<OrderLineItem> lineItems = IntStream.range(0, cart.lineItemSize()).mapToObj(cart::lineItem)
                .map(this::createOrderLineItem).toList();

        Order order = new Order(OrderId.generate(), userId, lineItems, receiver, payment, OrderStatus.PAID);

        paymentValidator.validate(payment, order);
        orderRepository.save(order);

        cart.clear();
        return order;
    }

    private OrderLineItem createOrderLineItem(CartLineItem cartLineItem) {
        Product product = productRepository.findById(cartLineItem.productId()).orElseThrow();
        List<OrderOption> options = cartLineItem.optionIds().stream()
                .map(optionId -> createOrderOption(cartLineItem, product, optionId)).toList();

        return new OrderLineItem(OrderLineItemId.generate(), product, options, cartLineItem.quantity());
    }

    private static OrderOption createOrderOption(CartLineItem cartLineItem, Product product, ProductOptionId optionId) {
        ProductOptionItemId itemId = cartLineItem.productOptionItemId(optionId);
        ProductOption productOption = product.optionById(optionId);
        ProductOptionItem productOptionItem = productOption.itemById(itemId);

        return new OrderOption(OrderOptionId.generate(), productOption, productOptionItem);
    }
}
