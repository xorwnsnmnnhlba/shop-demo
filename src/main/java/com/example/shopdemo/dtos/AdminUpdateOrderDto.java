package com.example.shopdemo.dtos;

import jakarta.validation.constraints.NotBlank;

public record AdminUpdateOrderDto(@NotBlank String status) {

}
