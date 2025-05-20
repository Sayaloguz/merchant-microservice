package com.sarawipay.merchant_microservice.Merchant.application.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantTypesDTO;

import java.util.List;

public interface MerchantGetUseCase {

    List<MerchantGenericModel> getByName(String name);

    MerchantGenericModel getById(String id);

    List<MerchantGenericModel> getAllMerchants();

    List<MerchantGenericModel> findMerchantsByClientId(String clientId);

    MerchantTypesDTO getAllMerchantTypes();

}
