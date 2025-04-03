package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output;

import com.sarawipay.merchant_microservice.Merchant.domain.enums.MerchantType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantOutputDTO {
    private String name;
    private String address;
    private MerchantType merchantType;

    // Necesitamos este setter para poder convertir el nombre a minúsculas
    public void setName(String name) {
        StringBuilder sb = new StringBuilder(name.length());
        sb.append(Character.toUpperCase(name.charAt(0)));
        sb.append(name.substring(1).toLowerCase());

        this.name = sb.toString();
    }

}