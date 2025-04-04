package com.sarawipay.merchant_microservice.Merchant.application;

import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantUpdateUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;
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
    public MerchantOutputDTO update(MerchantInputDTO merchantInputDTO, String pk, String sk) {

        Merchant merchant = merchantRepository.update(merchantInputDTO, pk, sk);
        MerchantOutputDTO merchantOutputDTO = merchantMappers.merchantToOutput(merchant);

        return merchantOutputDTO;

    }

}
