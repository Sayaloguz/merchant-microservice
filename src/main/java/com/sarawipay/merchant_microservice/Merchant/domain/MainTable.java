package com.sarawipay.merchant_microservice.Merchant.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "MainTable")
public class MainTable {

    @DynamoDBHashKey(attributeName = "PK")
    private String pk;

    @DynamoDBRangeKey(attributeName = "SK")
    private String sk;

    @DynamoDBAttribute(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "status")
    private String status;

    @DynamoDBIndexHashKey(attributeName = "gIndex2Pk", globalSecondaryIndexName = "gIndex2Pk")
    protected String gIndex2Pk;

    @DynamoDBAttribute(attributeName = "createTime")
    private String createTime;
}