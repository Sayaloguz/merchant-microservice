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

    private String pk;

    private String sk;

    private String id;

    private String name;

    private String address;

    private String merchantType;

    private String gIndexClient;


    // TODO: (Consejo César) Dos atributos para nombre merchant, uno para mostrar y otro para búsqueda


    /*
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
    */


}