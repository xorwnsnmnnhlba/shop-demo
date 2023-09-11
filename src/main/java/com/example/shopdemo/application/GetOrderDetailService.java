package com.example.shopdemo.application;

import com.example.shopdemo.dtos.ImageDto;
import com.example.shopdemo.dtos.OrderDetailDto;
import com.example.shopdemo.dtos.OrderLineItemDto;
import com.example.shopdemo.models.*;
import com.example.shopdemo.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetOrderDetailService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final OrderRepository orderRepository;

    public OrderDetailDto getOrderDetail(OrderId orderId, UserId userId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId).orElseThrow();
        return new OrderDetailDto(order.id().toString(), order.title(),
                mapToLineItemDtos(order), order.totalPrice().asLong(),
                order.status().toString(), order.orderedAt().format(DATE_TIME_FORMATTER));
    }

    private List<OrderLineItemDto> mapToLineItemDtos(Order order) {
        return IntStream.range(0, order.lineItemSize()).mapToObj(order::lineItem).map(this::mapToLineItemDto).toList();
    }

    private OrderLineItemDto mapToLineItemDto(OrderLineItem orderLineItem) {
        return new OrderLineItemDto(orderLineItem.id().toString(),
                mapToProductDto(orderLineItem), mapToOptionDtos(orderLineItem),
                orderLineItem.unitPrice().asLong(), orderLineItem.quantity(), orderLineItem.totalPrice().asLong());
    }

    private OrderLineItemDto.ProductDto mapToProductDto(OrderLineItem orderLineItem) {
        return new OrderLineItemDto.ProductDto(orderLineItem.productId().toString(), new ImageDto(""),
                orderLineItem.productName());
    }

    private List<OrderLineItemDto.OptionDto> mapToOptionDtos(OrderLineItem orderLineItem) {
        return IntStream.range(0, orderLineItem.optionSize()).mapToObj(orderLineItem::option)
                .map(this::mapToOptionDto).toList();
    }

    private OrderLineItemDto.OptionDto mapToOptionDto(OrderOption orderOption) {
        String optionName = orderOption.name();
        String optionItemName = orderOption.optionItem().name();

        return new OrderLineItemDto.OptionDto(optionName, new OrderLineItemDto.OptionItemDto(optionItemName));
    }

}
