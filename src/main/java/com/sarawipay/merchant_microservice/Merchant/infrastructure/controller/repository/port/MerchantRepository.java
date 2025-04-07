package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantRepository {

    void create(MerchantGenericModel merchantGenericModell);

    Merchant findById(String id);

    List<Merchant> findByName(String name);

    Merchant update(MerchantInputDTO merchantInputDTO, String pk, String sk);

    Optional<Merchant> clientMerchant(Merchant merchant);

}

// Nota: Los dos métodos "findBy" son muy parecidos entre ellos, una posible mejora sería hacer una función genérica para los tres