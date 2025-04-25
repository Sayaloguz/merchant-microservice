package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantUpdateRequestDTO {

    private String pk;

    private String sk;

    private String name;

    private String address;

    private String merchantType;

    private String gIndexClient;

}