package com.example.shopdemo.controllers.admin;

import com.example.shopdemo.application.GetOrderListService;
import com.example.shopdemo.dtos.AdminOrderListDto;
import com.example.shopdemo.security.AdminRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AdminRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final GetOrderListService getOrderListService;

    @GetMapping
    public AdminOrderListDto list() {
        return getOrderListService.getAdminOrderList();
    }

}
