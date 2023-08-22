package com.example.shopdemo.controllers;

import com.example.shopdemo.application.GetUserService;
import com.example.shopdemo.application.SignupService;
import com.example.shopdemo.dtos.SignupRequestDto;
import com.example.shopdemo.dtos.SignupResultDto;
import com.example.shopdemo.dtos.UserDto;
import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import com.example.shopdemo.security.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final SignupService signupService;

    private final GetUserService getUserService;

    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId id = new UserId(authUser.id());
        User user = getUserService.getUser(id);
        return UserDto.of(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignupResultDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        String accessToken = signupService.signup(signupRequestDto.email().trim(),
                signupRequestDto.name().trim(), signupRequestDto.password().trim());

        return new SignupResultDto(accessToken);
    }

}
