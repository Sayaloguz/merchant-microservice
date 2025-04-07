package com.sarawipay.merchant_microservice.Merchant.application;

import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantGetUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port.MerchantRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class MerchantGetUseCaseImpl implements MerchantGetUseCase {

    private final MerchantRepository merchantRepository;
    private final MerchantMappers merchantMappers;


    @Override
    public List<MerchantGenericModel> getByName(String name) {

        return merchantRepository.findByName(name);

    }


    @Override
    public MerchantGenericModel getById(String id) {

        return merchantRepository.findById(id);

    }

}
