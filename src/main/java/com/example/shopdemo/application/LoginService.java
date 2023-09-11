package com.example.shopdemo.application;

import com.example.shopdemo.security.AccessTokenGenerator;
import com.example.shopdemo.security.AuthUser;
import com.example.shopdemo.security.AuthUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final AuthUserDao authUserDao;

    private final PasswordEncoder passwordEncoder;

    private final AccessTokenGenerator accessTokenGenerator;

    public String login(String email, String password) {
        return authUserDao.findByEmail(email)
                .filter(authUser -> matchPassword(password, authUser))
                .map(this::generateAccessToken)
                .orElseThrow(() -> new BadCredentialsException("Login failed"));
    }

    public String loginAdmin(String email, String password) {
        return authUserDao.findByEmail(email)
                .filter(authUser -> matchPassword(password, authUser))
                .filter(AuthUser::isAdmin)
                .map(this::generateAccessToken)
                .orElseThrow(() -> new BadCredentialsException("Login failed"));
    }

    private boolean matchPassword(String password, AuthUser authUser) {
        return passwordEncoder.matches(password, authUser.password());
    }

    private String generateAccessToken(AuthUser authUser) {
        String id = authUser.id();
        String accessToken = accessTokenGenerator.generate(id);
        authUserDao.addAccessToken(id, accessToken);
        return accessToken;
    }

}
