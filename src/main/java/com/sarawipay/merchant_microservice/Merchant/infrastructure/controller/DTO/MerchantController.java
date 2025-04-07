package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.application.MerchantGetUseCaseImpl;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantAddUseCase;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantUpdateUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.Merchant;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantUpdateRequestDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.FullMerchantOutputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantIdDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

        List<MerchantGenericModel> res = merchantGetUseCase.getByName(name);

        List<MerchantOutputDTO> merchantOutputDTOList = res.stream()
                .map(merchantMappers::modelToOutput)
                .collect(Collectors.toList());

        return merchantOutputDTOList;

    }


    @GetMapping("/getById/{id}")
    public FullMerchantOutputDTO getById(@PathVariable String id) {

        return merchantMappers.modelToFullOutput(merchantGetUseCase.getById(id));

    }

    @GetMapping("/getById/{id}/{simpleOutput}")
    public MerchantIdDTO getByIdSimple(@PathVariable String id, @PathVariable String simpleOutput) {

        if(simpleOutput.equalsIgnoreCase("simpleOutput")) {
            return merchantMappers.modelToIdDTO(merchantGetUseCase.getById(id));
        } else {
            throw new IllegalArgumentException("Valor inválido para simpleOutput");
        }

    }




    @PutMapping("/update")
    public MerchantOutputDTO update(@RequestBody MerchantUpdateRequestDTO merchantUpdate) {

        return merchantUpdateUseCase.update(merchantUpdate.getMerchantInputDTO(), merchantUpdate.getPk(), merchantUpdate.getSk());

    }

}