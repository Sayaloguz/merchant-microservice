package com.sarawipay.merchant_microservice.Merchant.domain.mappers;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantUpdateRequestDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.FullMerchantOutputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantIdDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface MerchantMappers {

    MerchantGenericModel merchantToModel(Merchant merchant);

    Merchant modelToMerchant(MerchantGenericModel merchantGenericModel);

    MerchantGenericModel inputToModel(MerchantInputDTO merchantInputDTO);

    MerchantIdDTO modelToIdDTO(MerchantGenericModel merchantGenericModel);

    MerchantGenericModel updateRequestToModel(MerchantUpdateRequestDTO merchantUpdateRequestDTO);

    @Mapping(target = "name", source = "name", qualifiedByName = "capitalizeName")
    MerchantOutputDTO modelToOutput(MerchantGenericModel merchantGenericModel);

    @Mapping(target = "name", source = "name", qualifiedByName = "capitalizeName")
    FullMerchantOutputDTO modelToFullOutput(MerchantGenericModel merchantGenericModel);


    @Named("capitalizeName")
    public static String capitalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }
}

