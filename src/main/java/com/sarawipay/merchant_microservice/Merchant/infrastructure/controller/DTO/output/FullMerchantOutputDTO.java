package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullMerchantOutputDTO {


    private String id;
    private String status;
    private String createTime;
    private String name;
    private String address;
    private String merchantType;

}
