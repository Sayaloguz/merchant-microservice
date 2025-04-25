package com.sarawipay.merchant_microservice.Merchant.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
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

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "merchantType")
    private MerchantType merchantType;

    @DynamoDBIndexHashKey(attributeName = "gIndexClient", globalSecondaryIndexName = "gIndexClient")
    private String gIndexClient;

}