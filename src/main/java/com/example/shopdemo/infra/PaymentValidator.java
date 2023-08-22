package com.example.shopdemo.infra;

import com.example.shopdemo.infra.dtos.PortonePaymentDto;
import com.example.shopdemo.infra.dtos.PortoneTokenRequestDto;
import com.example.shopdemo.infra.dtos.PortoneTokenResultDto;
import com.example.shopdemo.models.Money;
import com.example.shopdemo.models.Order;
import com.example.shopdemo.models.Payment;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@ToString
public class PaymentValidator {

    private final String restApiKey;

    private final String restApiSecret;

    private final RestTemplate restTemplate;


    public PaymentValidator(@Value("${portone.rest_api_key}") String restApiKey,
                            @Value("${portone.rest_api_secret}") String restApiSecret, RestTemplateBuilder builder) {
        this.restApiKey = restApiKey;
        this.restApiSecret = restApiSecret;
        this.restTemplate = builder.build();
    }

    public void validate(Payment payment, Order order) {
        String accessToken = getAccessToken();
        Long amount = fetchPaymentAmount(payment, accessToken);

        if (!order.totalPrice().equals(new Money(amount))) {
            throw new IllegalArgumentException("Payment amount doesn't match");
        }
    }

    private String getAccessToken() {
        String url = "https://api.iamport.kr/users/getToken";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        PortoneTokenRequestDto requestDto = new PortoneTokenRequestDto(restApiKey, restApiSecret);
        HttpEntity<PortoneTokenRequestDto> request = new HttpEntity<>(requestDto, headers);

        PortoneTokenResultDto resultDto = restTemplate.postForObject(url, request, PortoneTokenResultDto.class);
        PortoneTokenResultDto.Response responseDto = resultDto.response();
        return responseDto.accessToken();
    }

    private Long fetchPaymentAmount(Payment payment, String accessToken) {
        String url = "https://api.iamport.kr/payments/" + payment.transactionId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<PortonePaymentDto> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, PortonePaymentDto.class);

        PortonePaymentDto paymentDto = response.getBody();
        PortonePaymentDto.Response responseDto = paymentDto.response();
        return responseDto.amount();
    }

}
