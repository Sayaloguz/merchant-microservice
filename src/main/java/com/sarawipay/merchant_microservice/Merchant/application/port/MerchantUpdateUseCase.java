package com.sarawipay.merchant_microservice.Merchant.application.port;

import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;

public interface MerchantUpdateUseCase {

    MerchantOutputDTO update(MerchantInputDTO merchantInputDTO, String pk, String sk);

}
