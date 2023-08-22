package com.example.shopdemo.controllers;

import com.example.shopdemo.application.GetProductDetailService;
import com.example.shopdemo.application.GetProductListService;
import com.example.shopdemo.dtos.ProductDetailDto;
import com.example.shopdemo.dtos.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final GetProductListService getProductListService;

    private final GetProductDetailService getProductDetailService;

    @GetMapping
    public ProductListDto list(@RequestParam(required = false) String categoryId) {
        return getProductListService.getProductListDto(categoryId);
    }

    @GetMapping("/{id}")
    public ProductDetailDto detail(@PathVariable String id) {
        return getProductDetailService.getProductDetailDto(id);
    }

}
