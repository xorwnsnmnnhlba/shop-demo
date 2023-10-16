package com.example.shopdemo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class Receiver {

    @Column(name = "receiver_name")
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    public String name() {
        return name;
    }

    public Address address() {
        return address;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }
    
}
