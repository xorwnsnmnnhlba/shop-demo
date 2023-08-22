package com.example.shopdemo.application;

import com.example.shopdemo.security.AuthUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LogoutService {

    private final AuthUserDao authUserDao;

    public void logout(String accessToken) {
        authUserDao.removeAccessToken(accessToken);
    }
    
}
