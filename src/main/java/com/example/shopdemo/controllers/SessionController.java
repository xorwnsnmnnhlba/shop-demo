package com.example.shopdemo.controllers;

import com.example.shopdemo.application.LoginService;
import com.example.shopdemo.application.LogoutService;
import com.example.shopdemo.dtos.LoginRequestDto;
import com.example.shopdemo.dtos.LoginResultDto;
import com.example.shopdemo.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final LoginService loginService;
    
    private final LogoutService logoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(@RequestBody LoginRequestDto loginRequestDto) {
        String accessToken = loginService.login(loginRequestDto.email(), loginRequestDto.password());
        return new LoginResultDto(accessToken);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginFailed() {
        return "Bad Request";
    }

    @DeleteMapping
    public String logout(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        logoutService.logout(authUser.accessToken());

        return "Logout";
    }
}
