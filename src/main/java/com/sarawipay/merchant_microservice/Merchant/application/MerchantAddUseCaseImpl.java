package com.sarawipay.merchant_microservice.Merchant.application;

import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantAddUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
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
    public void addMerchant(MerchantGenericModel generic) {

        String id = UUID.randomUUID().toString();


        generic.setId(id);
        generic.setPk("merchant#" + id);
        generic.setSk(UUID.randomUUID().toString()); // Lo propio sería comprobar que el cliente existiera, pero por agilizar me salto esa parte
        generic.setStatus("ACTIVE");
        generic.setCreateTime(String.valueOf(new Date()));
        generic.setGIndex2Pk("entityMerchant");


        merchantRepository.create(generic);
    }

}
