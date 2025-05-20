package com.sarawipay.merchant_microservice.Merchant.application;

import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantGetUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.enums.MerchantType;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantTypesDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port.MerchantRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public List<MerchantGenericModel> getAllMerchants() {

        return merchantRepository.findAll();
    }

    @Override
    public List<MerchantGenericModel> findMerchantsByClientId(String clientId) {

        return merchantRepository.findMerchantsByClientId(clientId);

    }

    // Solo se van a devolver MerchantTypes en esta ocasión, así que se opta por no hacer un modelo genérico
    @Override
    public MerchantTypesDTO getAllMerchantTypes() {
        List<String> merchantTypes = Arrays.stream(MerchantType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return new MerchantTypesDTO(merchantTypes);
    }

}
