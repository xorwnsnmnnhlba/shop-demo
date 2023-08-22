package com.example.shopdemo.models;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Money {

    public static final Money ZERO = new Money(0L);

    private Long amount;

    public Long asLong() {
        return amount;
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier);
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public Money plus(Money other) {
        return new Money(amount + other.amount);
    }
}
