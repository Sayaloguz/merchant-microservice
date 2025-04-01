package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.repository.port;

import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import java.util.Optional;

public interface MerchantRepository {
    Optional<Merchant> clientMerchant(Merchant merchant);
}
