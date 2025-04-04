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

    private MerchantInputDTO merchantInputDTO;

    private String pk;

    private String sk;

}