package com.sarawipay.merchant_microservice.Merchant.domain.mappers;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MerchantMappers {

    Merchant inputToMerchant(MerchantInputDTO merchantInputDTO);

    MerchantOutputDTO merchantToOutput(Merchant merchant);

    MerchantGenericModel merchantToModel(Merchant merchant);

    Merchant modelToMerchant(MerchantGenericModel merchantGenericModel);

    MerchantGenericModel inputToModel(MerchantInputDTO merchantInputDTO);

    MerchantOutputDTO modelToOutput(MerchantGenericModel merchantGenericModel);

}