package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.domain.enums.MerchantType;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port.MerchantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@Repository
public class MerchantRepositoryImpl implements MerchantRepository {


    private final DynamoDBMapper dynamoDBMapper;


    @Override
    public Merchant create(Merchant merchant) {

        dynamoDBMapper.save(merchant);
        return merchant;

    }


    @Override
    public Merchant findById(String id) {

        String pkGsi = "gIndex2Pk";

        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#pkAttr", pkGsi);
        expressionAttributeNames.put("#idAttr", "id");

        Map<String, AttributeValue> expressionAtributeValues = new HashMap<>();
        expressionAtributeValues.put(":pkVal", new AttributeValue().withS("entityMerchant")); // Solo buscamos merchant
        expressionAtributeValues.put(":id", new AttributeValue().withS(id));


        DynamoDBQueryExpression<Merchant> query = new DynamoDBQueryExpression<Merchant>()
                .withIndexName("gIndex2Pk")
                .withConsistentRead(false)
                .withKeyConditionExpression("#pkAttr = :pkVal")
                .withFilterExpression("#idAttr = :id")
                // Asignación de nombres y valores
                .withExpressionAttributeNames(expressionAttributeNames)
                .withExpressionAttributeValues(expressionAtributeValues);

        List<Merchant> res = dynamoDBMapper.query(Merchant.class, query);

        return res.get(0);
    }


    @Override
    public List<Merchant> findByName(String name) {

        String pkGsi = "gIndex2Pk"; // PK de GSI
        String lwrCaseName = name.toLowerCase(); // Comparación en minúsculas

        Map<String, String> expressionAttributeNames = new HashMap<>(); // Evita posibles conflictos con palabras reservadas
        expressionAttributeNames.put("#pkAttr", pkGsi);
        expressionAttributeNames.put("#nameAttr", "name");

        Map<String, AttributeValue> expressionAtributeValues = new HashMap<>();
        expressionAtributeValues.put(":pkVal", new AttributeValue().withS("entityMerchant")); // Solo buscamos comercios
        expressionAtributeValues.put(":name", new AttributeValue().withS(lwrCaseName));


        DynamoDBQueryExpression<Merchant> query = new DynamoDBQueryExpression<Merchant>()
                .withIndexName("gIndex2Pk")
                .withConsistentRead(false)
                .withKeyConditionExpression("#pkAttr = :pkVal")
                .withFilterExpression("contains(#nameAttr, :name)")
                // Asignación de nombres y valores
                .withExpressionAttributeNames(expressionAttributeNames)
                .withExpressionAttributeValues(expressionAtributeValues);

        List<Merchant> res = dynamoDBMapper.query(Merchant.class, query);

        return res;

    }


    @Override
    public Merchant update(MerchantInputDTO merchantInputDTO, String pk, String sk) {

        Merchant existingMerchant = dynamoDBMapper.load(Merchant.class, pk, sk);

        if (existingMerchant != null) {

            existingMerchant.setName(merchantInputDTO.getName());
            existingMerchant.setAddress(merchantInputDTO.getAddress());
            existingMerchant.setMerchantType(MerchantType.valueOf(merchantInputDTO.getMerchantType()));

            dynamoDBMapper.save(existingMerchant);

        }

        return existingMerchant;

    }

    @Override
    public Optional<Merchant> clientMerchant(Merchant merchant) {

        return Optional.empty();

    }

}
