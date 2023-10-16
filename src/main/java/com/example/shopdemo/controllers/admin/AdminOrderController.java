package com.example.shopdemo.controllers.admin;

import com.example.shopdemo.application.GetAdminOrderDetailService;
import com.example.shopdemo.application.GetOrderListService;
import com.example.shopdemo.application.UpdateOrderService;
import com.example.shopdemo.dtos.AdminOrderDetailDto;
import com.example.shopdemo.dtos.AdminOrderListDto;
import com.example.shopdemo.dtos.AdminUpdateOrderDto;
import com.example.shopdemo.models.OrderId;
import com.example.shopdemo.models.OrderStatus;
import com.example.shopdemo.security.AdminRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AdminRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final GetOrderListService getOrderListService;

    private final GetAdminOrderDetailService getAdminOrderDetailService;

    private final UpdateOrderService updateOrderService;

    @GetMapping
    public AdminOrderListDto list() {
        return getOrderListService.getAdminOrderList();
    }

    @GetMapping("/{id}")
    public AdminOrderDetailDto detail(@PathVariable String id) {
        OrderId orderId = new OrderId(id);
        return getAdminOrderDetailService.getOrderDetail(orderId);
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable String id, @Valid @RequestBody AdminUpdateOrderDto orderDto) {
        OrderId orderId = new OrderId(id);
        OrderStatus status = OrderStatus.of(orderDto.status());
        updateOrderService.updateOrderStatus(orderId, status);
        return "Updated";
    }

}
