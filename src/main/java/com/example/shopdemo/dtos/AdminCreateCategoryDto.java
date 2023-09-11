package com.example.shopdemo.dtos;

import jakarta.validation.constraints.NotBlank;

public record AdminCreateCategoryDto(@NotBlank String name) {
    
}
