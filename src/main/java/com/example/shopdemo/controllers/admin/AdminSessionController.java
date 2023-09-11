package com.example.shopdemo.controllers.admin;

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
@RequiredArgsConstructor
@RequestMapping("/admin/session")
public class AdminSessionController {

    private final LoginService loginService;

    private final LogoutService logoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto loginAdmin(@RequestBody LoginRequestDto loginRequestDto) {
        String accessToken = loginService.loginAdmin(loginRequestDto.email(), loginRequestDto.password());
        return new LoginResultDto(accessToken);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginAdminFailed() {
        return "Bad Request";
    }

    @DeleteMapping
    public String logoutAdmin(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        logoutService.logout(authUser.accessToken());

        return "Logout";
    }

}
