package com.sarawipay.merchant_microservice.Merchant.application.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;

public interface MerchantAddUseCase {

    MerchantGenericModel addMerchant(MerchantGenericModel merchantGenericModel);

}
