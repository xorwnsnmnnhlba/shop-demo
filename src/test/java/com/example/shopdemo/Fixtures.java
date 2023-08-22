package com.example.shopdemo;

import com.example.shopdemo.models.*;

import java.util.List;
import java.util.NoSuchElementException;

public class Fixtures {

    public static Product product(String name) {
        if (name.equals("맨투맨")) {
            return new Product(new ProductId("0BV000PRO0001"), new CategoryId("0BV000CAT0001"),
                    List.of(new Image("http://example.com/product-01.jpg")), "맨투맨", new Money(128_000L),
                    List.of(productOption("색상"), productOption("크기")), "편하게 입을 수 있는 맨투맨");
        }

        if (name.equals("셔츠")) {
            return new Product(new ProductId("0BV000PRO0002"), new CategoryId("0BV000CAT0002"),
                    List.of(new Image("http://example.com/product-02.jpg")), "셔츠", new Money(123_000L),
                    List.of(), "Shirt");
        }

        throw new NoSuchElementException("Product - name: " + name);
    }

    private static ProductOption productOption(String name) {
        if (name.equals("색상")) {
            return new ProductOption(new ProductOptionId("0BV000OPT0001"), "색상",
                    List.of(productOptionItem("Black"), productOptionItem("White")));
        }

        if (name.equals("크기")) {
            return new ProductOption(new ProductOptionId("0BV000OPT0002"), "크기",
                    List.of(productOptionItem("S"), productOptionItem("M"), productOptionItem("L")));
        }

        throw new NoSuchElementException("ProductOption - name: " + name);
    }

    private static ProductOptionItem productOptionItem(String name) {
        if (name.equals("Black")) {
            return new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0001"), "Black");
        }

        if (name.equals("White")) {
            return new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0002"), "White");
        }

        if (name.equals("S")) {
            return new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0003"), "S");
        }

        if (name.equals("M")) {
            return new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0004"), "M");
        }

        if (name.equals("L")) {
            return new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0005"), "L");
        }

        throw new NoSuchElementException("ProductOptionItem - name: " + name);
    }

    public static Receiver receiver(String name) {
        return new Receiver(name, new Address("서울 성동구 상원12길 34", "ㅇㅇㅇ호",
                new PostalCode("04790")), new PhoneNumber("01012345678"));
    }

    public static Payment payment() {
        return new Payment("PaymentMerchantID", "PaymentTransactionID");
    }

}
