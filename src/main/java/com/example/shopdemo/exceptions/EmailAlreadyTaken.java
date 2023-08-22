package com.example.shopdemo.exceptions;

public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String email) {
        super("Email has already been taken: " + email);
    }

}
