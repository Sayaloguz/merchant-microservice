package com.sarawipay.merchant_microservice.Merchant.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantGenericModel {

    private String pk;
    private String sk;
    private String id;
    private String status;
    private String createTime;
    private String gIndex2Pk;
    private String name;
    private String address;
    private String merchantType;

}
