package com.sarawipay.merchant_microservice.Merchant.application;

import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantUpdateUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port.MerchantRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
public class MerchantUpdateUseCaseImpl implements MerchantUpdateUseCase {

    private final MerchantRepository merchantRepository;
    private final MerchantMappers merchantMappers;


    @Override
    public void update(MerchantGenericModel generic) {

        merchantRepository.update(generic);

    }

}
