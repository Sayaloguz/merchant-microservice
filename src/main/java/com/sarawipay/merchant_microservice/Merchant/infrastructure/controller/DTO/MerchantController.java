package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.application.MerchantGetUseCaseImpl;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantAddUseCase;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantUpdateUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantUpdateRequestDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MerchantController {

    private final MerchantAddUseCase merchantAddUseCase;

    private final MerchantGetUseCaseImpl merchantGetUseCase;

    private final MerchantUpdateUseCase merchantUpdateUseCase;
    private final MerchantMappers merchantMappers;


    @PostMapping("/create")
    public void addMerchant(@Valid @RequestBody MerchantInputDTO merchantInputDTO) {

        MerchantGenericModel generic = merchantMappers.inputToModel(merchantInputDTO);
        merchantAddUseCase.addMerchant(generic);

    }


    @GetMapping("/getByName/{name}")
    public List<MerchantOutputDTO> getByName(@PathVariable String name) {

        return merchantGetUseCase.getByName(name);

    }


    @GetMapping("/getById/{id}")
    public MerchantOutputDTO getById(@PathVariable String id) {

        return merchantGetUseCase.getById(id);

    }


    @PutMapping("/update")
    public MerchantOutputDTO update(@RequestBody MerchantUpdateRequestDTO merchantUpdate) {

        return merchantUpdateUseCase.update(merchantUpdate.getMerchantInputDTO(), merchantUpdate.getPk(), merchantUpdate.getSk());

    }

}