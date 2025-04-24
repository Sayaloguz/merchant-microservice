package com.sarawipay.merchant_microservice.Merchant.application;

import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantDeleteUseCase;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port.MerchantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class MerchantDeleteUseCaseImpl implements MerchantDeleteUseCase {

    private final MerchantRepository merchantRepository;


    @Override
    public void deleteMerchant(String id) {
        merchantRepository.delete(id);
    }
}
