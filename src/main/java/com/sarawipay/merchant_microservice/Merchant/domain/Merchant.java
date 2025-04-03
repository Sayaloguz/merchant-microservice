package com.sarawipay.merchant_microservice.Merchant.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.sarawipay.merchant_microservice.Merchant.domain.enums.MerchantType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Merchant extends MainTable {

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "address")
    private String address;

    @DynamoDBAttribute(attributeName = "merchantType")
    private MerchantType merchantType;


}