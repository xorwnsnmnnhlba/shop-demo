package com.example.shopdemo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class Payment {

    @Column(name = "payment_merchant_id")
    private String merchantId;

    @Column(name = "payment_transaction_id")
    private String transactionId;

    public String transactionId() {
        return transactionId;
    }

}
