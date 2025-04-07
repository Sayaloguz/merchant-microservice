package com.sarawipay.merchant_microservice.Merchant.application.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;

import java.util.List;

public interface MerchantGetUseCase {

    List<MerchantGenericModel> getByName(String name);

    MerchantOutputDTO getById(String id);

}
