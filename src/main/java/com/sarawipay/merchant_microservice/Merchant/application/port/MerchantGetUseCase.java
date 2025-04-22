package com.sarawipay.merchant_microservice.Merchant.application.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;

import java.util.List;

public interface MerchantGetUseCase {

    List<MerchantGenericModel> getByName(String name);

    MerchantGenericModel getById(String id);

    List<MerchantGenericModel> getAllMerchants();
}
