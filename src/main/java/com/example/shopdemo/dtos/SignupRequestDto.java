package com.example.shopdemo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(@NotBlank @Email String email, @NotBlank String name, @NotBlank String password) {
}
