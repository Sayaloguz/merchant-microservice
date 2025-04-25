package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantOutputDTO {

    private String name;

    private String address;

    private String merchantType;

    private String gIndexClient;


    // Necesitamos este setter para poder convertir el nombre a minúsculas
    public void setName(String name) {

        StringBuilder sb = new StringBuilder(name.length());
        sb.append(Character.toUpperCase(name.charAt(0)));
        sb.append(name.substring(1).toLowerCase());

        this.name = sb.toString();
    }

    public void setMerchantType(String merchantType) {
        String prefix = "MERCHANT_TYPE_";
        if (!merchantType.startsWith(prefix)) {
            throw new IllegalArgumentException("Invalid merchant type");
        }
        String merchantTypeWithoutPrefix = merchantType.substring(prefix.length());

        String[] words = merchantTypeWithoutPrefix.split("_");

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()){
                sb.append(word.substring(0, 1).toUpperCase());
                sb.append(word.substring(1).toLowerCase());
                sb.append(" ");
            }
        }

        this.merchantType = sb.toString().trim();
    }

}