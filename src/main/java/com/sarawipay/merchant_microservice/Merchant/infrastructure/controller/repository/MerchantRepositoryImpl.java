package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.domain.enums.MerchantType;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.FullMerchantOutputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port.MerchantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@Repository
public class MerchantRepositoryImpl implements MerchantRepository {


    private final DynamoDBMapper dynamoDBMapper;
    private final MerchantMappers merchantMappers;


    @Override
    public MerchantGenericModel create(MerchantGenericModel generic) {

        generic.setName(generic.getName().toLowerCase());
        Merchant merchant = merchantMappers.modelToMerchant(generic);

        dynamoDBMapper.save(merchant);
        return merchantMappers.merchantToModel(merchant);
    }


    @Override
    public MerchantGenericModel findById(String id) {

        Map<String, AttributeValue> expressionAtributeValues = new HashMap<>();
        expressionAtributeValues.put(":pkVal", new AttributeValue().withS("entityMerchant")); // Solo buscamos merchant
        expressionAtributeValues.put(":id", new AttributeValue().withS(id));

        DynamoDBQueryExpression<Merchant> query = new DynamoDBQueryExpression<Merchant>()
                .withIndexName("gIndex2Pk")
                .withConsistentRead(false)
                .withKeyConditionExpression("gIndex2Pk = :pkVal")
                .withFilterExpression("id = :id")
                .withExpressionAttributeValues(expressionAtributeValues);

        List<Merchant> res = dynamoDBMapper.query(Merchant.class, query);

        if (res.isEmpty()) {
            return null;
        }

        return merchantMappers.merchantToModel(res.get(0));
    }


    @Override
    public List<MerchantGenericModel> findByName(String name) {

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


        List<Merchant> entities = dynamoDBMapper.query(Merchant.class, query);

        List<MerchantGenericModel> res = entities.stream()
                .map(merchantMappers::merchantToModel)
                .collect(Collectors.toList());

        return res;

    }


    @Override
    public MerchantGenericModel update(MerchantGenericModel generic) {

        Merchant existingMerchant = merchantMappers.modelToMerchant(this.findById(generic.getId()));

        if (existingMerchant != null) {
            existingMerchant.setName(generic.getName().toLowerCase());
            existingMerchant.setAddress(generic.getAddress());
            existingMerchant.setMerchantType(MerchantType.valueOf(generic.getMerchantType()));

            dynamoDBMapper.save(existingMerchant);
        }

        return merchantMappers.merchantToModel(existingMerchant);
    }


    @Override
    public List<MerchantGenericModel> findAll() {

        String pkGsi = "gIndex2Pk"; // PK de GSI

        Map<String, AttributeValue> expressionAtributeValues = new HashMap<>();
        expressionAtributeValues.put(":pkVal", new AttributeValue().withS("entityMerchant")); // Solo buscamos comercios

        DynamoDBQueryExpression<Merchant> query = new DynamoDBQueryExpression<Merchant>()
                .withIndexName("gIndex2Pk")
                .withConsistentRead(false)
                .withKeyConditionExpression("gIndex2Pk = :pkVal")
                // Asignación de nombres y valores
                .withExpressionAttributeValues(expressionAtributeValues);

        List<Merchant> entities = dynamoDBMapper.query(Merchant.class, query);
        List<MerchantGenericModel> res = entities.stream()
                .map(merchantMappers::merchantToModel)
                .collect(Collectors.toList());

        return res;

    }


    public MerchantGenericModel deleteMerchant(String id) {
        Map<String, AttributeValue> attrValues = new HashMap<>();
        attrValues.put(":pkVal", new AttributeValue().withS("entityMerchant"));
        attrValues.put(":idVal", new AttributeValue().withS(id));

        DynamoDBQueryExpression<Merchant> query = new DynamoDBQueryExpression<Merchant>()
                .withIndexName("gIndex2Pk")
                .withConsistentRead(false)
                .withKeyConditionExpression("gIndex2Pk = :pkVal")
                .withFilterExpression("id = :idVal")
                .withExpressionAttributeValues(attrValues);

        List<Merchant> results = dynamoDBMapper.query(Merchant.class, query);

        if (results.isEmpty()) {
            throw new NoSuchElementException("Merchant no encontrado. Id solicitada: " + id);
        }

        Merchant merchant = results.get(0);
        dynamoDBMapper.delete(merchant);

        return merchantMappers.merchantToModel(merchant);
    }


    public List<MerchantGenericModel> findMerchantsByClientId(String clientId) {

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":gsiPkVal", new AttributeValue().withS(clientId));

        DynamoDBQueryExpression<Merchant> query = new DynamoDBQueryExpression<Merchant>()
                .withIndexName("gIndexClient")
                .withConsistentRead(false)
                .withKeyConditionExpression("gIndexClient = :gsiPkVal")
                .withExpressionAttributeValues(expressionAttributeValues);

        List<Merchant> res = dynamoDBMapper.query(Merchant.class, query);

        return res.stream()
                .map(merchantMappers::merchantToModel)
                .collect(Collectors.toList());
    }

}
