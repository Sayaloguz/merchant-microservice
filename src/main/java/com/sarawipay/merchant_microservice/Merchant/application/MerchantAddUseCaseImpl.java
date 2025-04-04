package com.sarawipay.merchant_microservice.Merchant.application;

import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantAddUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port.MerchantRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Service
public class MerchantAddUseCaseImpl implements MerchantAddUseCase {

    private final MerchantRepository merchantRepository;
    private final MerchantMappers merchantMappers;

    @Override
    public Merchant addMerchant(MerchantInputDTO merchantInputDTO, String idClient) {

        Merchant merchant = merchantMappers.inputToMerchant(merchantInputDTO);

        String id = UUID.randomUUID().toString();


        merchant.setId(id);
        merchant.setPk("merchant#" + id);
        merchant.setSk(idClient); // Lo propio sería comprobar que el cliente existiera, pero por agilizar me salto esa parte
        merchant.setStatus("ACTIVE");
        merchant.setCreateTime(String.valueOf(new Date()));
        merchant.setGIndex2Pk("entityMerchant");


        merchantRepository.create(merchant);

        return merchant;
    }

}
