package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantTypesDTO {

    private List<String> merchantTypes;

}
