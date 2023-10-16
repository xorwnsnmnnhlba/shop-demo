package com.example.shopdemo.models;

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
public class Address {

    private String address1;

    private String address2;

    @Embedded
    private PostalCode postalCode;

    public String address1() {
        return address1;
    }

    public String address2() {
        return address2;
    }

    public PostalCode postalCode() {
        return postalCode;
    }
    
}
