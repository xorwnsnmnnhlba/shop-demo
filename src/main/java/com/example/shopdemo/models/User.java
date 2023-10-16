package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.shopdemo.models.Role.ROLE_ADMIN;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @EmbeddedId
    private UserId id;

    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserId id, String email, String name, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public UserId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public Role role() {
        return role;
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean isAdmin() {
        return role.equals(ROLE_ADMIN);
    }
}
