package com.sarawipay.merchant_microservice.Merchant.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.sarawipay.merchant_microservice.Merchant.domain.enums.MerchantType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@RequiredArgsConstructor
@DynamoDBTable(tableName = "MainTable")
public class Merchant extends MainTable{
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "address")
    private String address;
    @DynamoDBAttribute(attributeName = "merchantType")
    private MerchantType merchantType;
}