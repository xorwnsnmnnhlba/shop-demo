package com.example.shopdemo.application;

import com.example.shopdemo.exceptions.EmailAlreadyTaken;
import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import com.example.shopdemo.repositories.UserRepository;
import com.example.shopdemo.security.AccessTokenGenerator;
import com.example.shopdemo.security.AuthUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.shopdemo.models.Role.ROLE_USER;

@Service
@Transactional
@RequiredArgsConstructor
public class SignupService {

    private final AuthUserDao authUserDao;

    private final PasswordEncoder passwordEncoder;

    private final AccessTokenGenerator accessTokenGenerator;

    private final UserRepository userRepository;

    public String signup(String email, String name, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyTaken(email);
        }

        UserId userId = createUser(email, name, password);
        return createAccessToken(userId);
    }

    private UserId createUser(String email, String name, String password) {
        UserId userId = UserId.generate();
        User user = new User(userId, email, name, ROLE_USER);
        user.changePassword(password, passwordEncoder);
        userRepository.save(user);
        return userId;
    }

    private String createAccessToken(UserId userId) {
        String accessToken = accessTokenGenerator.generate(userId.toString());
        authUserDao.addAccessToken(userId.toString(), accessToken);
        return accessToken;
    }

}
