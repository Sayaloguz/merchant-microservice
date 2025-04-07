package com.sarawipay.merchant_microservice.Merchant.application.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;

public interface MerchantAddUseCase {

    void addMerchant(MerchantGenericModel merchantGenericModel);

}
