package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantRepository {

    MerchantGenericModel create(MerchantGenericModel merchantGenericModell);

    MerchantGenericModel findById(String id);

    List<MerchantGenericModel> findByName(String name);

    MerchantGenericModel update(MerchantGenericModel merchantGenericModell);

    List<MerchantGenericModel> findAll();

    MerchantGenericModel deleteMerchant(String id);

    List<MerchantGenericModel> findMerchantsByClientId(String clientId);
}

// TODO: (Consejo Guille) Los dos métodos "findBy" son muy parecidos entre ellos, una posible mejora sería hacer una función genérica para los tres