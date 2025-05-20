package com.sarawipay.merchant_microservice.Merchant.application.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;

public interface MerchantUpdateUseCase {

    MerchantGenericModel update(MerchantGenericModel merchantGenericModel);

}
