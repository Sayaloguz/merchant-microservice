package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.application.MerchantGetUseCaseImpl;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantAddUseCase;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantDeleteUseCase;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantUpdateUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantUpdateRequestDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.FullMerchantOutputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantIdDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;


import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

@Api(value = "API REST del microservicio de merchants")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MerchantController {

    private final MerchantAddUseCase merchantAddUseCase;

    private final MerchantGetUseCaseImpl merchantGetUseCase;

    private final MerchantUpdateUseCase merchantUpdateUseCase;

    private final MerchantDeleteUseCase merchantDeleteUseCase;

    private final MerchantMappers merchantMappers;


    @PostMapping("/create")
    @ApiOperation(value = "Crear un nuevo merchant")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Comercio creado exitosamente", response = Map.class),
    })
    public ResponseEntity<Map<String, Object>> addMerchant(
            @ApiParam(value = "Datos del merchant a crear", required = true)
            @Valid @RequestBody MerchantInputDTO merchantInputDTO) {

        MerchantGenericModel generic = merchantMappers.inputToModel(merchantInputDTO);
        merchantAddUseCase.addMerchant(generic);


        // Tal como está siempre va a devolver el mismo mensaje, pero por agilizar la entrega lo dejo así
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comercio creado exitosamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/getByName/{name}")
    @ApiOperation(value = "Buscar merchants por nombre")
    public List<MerchantOutputDTO> getByName(
            @ApiParam(value = "Nombre del merchant a buscar", required = true)
            @PathVariable String name) {

        List<MerchantGenericModel> res = merchantGetUseCase.getByName(name);

        List<MerchantOutputDTO> merchantOutputDTOList = res.stream()
                .map(merchantMappers::modelToOutput)
                .collect(Collectors.toList());

        return merchantOutputDTOList;

    }


    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Buscar merchant por ID")
    public FullMerchantOutputDTO getById(
            @ApiParam(value = "ID del merchant a buscar", required = true)
            @PathVariable String id) {

        return merchantMappers.modelToFullOutput(merchantGetUseCase.getById(id));

    }


    @GetMapping("/getById/{id}/{simpleOutput}")
    @ApiOperation(value = "Buscar merchant por ID con salida simple")
    public MerchantIdDTO getByIdSimple(
            @ApiParam(value = "ID del merchant a buscar", required = true)
            @PathVariable String id,
            @ApiParam(value = "Tipo de salida", required = true)
            @PathVariable String simpleOutput) {

        if (simpleOutput.equalsIgnoreCase("simpleOutput")) {
            return merchantMappers.modelToIdDTO(merchantGetUseCase.getById(id));
        } else {
            throw new IllegalArgumentException("Valor inválido para simpleOutput");
        }

    }


    @PutMapping("/update")
    @ApiOperation(value = "Actualizar un merchant")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Comercio actualizado exitosamente", response = Map.class),
    })
    public ResponseEntity<Map<String, Object>> update(
            @ApiParam(value = "Datos del merchant a actualizar", required = true)
            @RequestBody MerchantUpdateRequestDTO merchantUpdate) {

        MerchantGenericModel generic = merchantMappers.updateRequestToModel(merchantUpdate);
        merchantUpdateUseCase.update(generic);

        // Tal como está siempre va a devolver el mismo mensaje, pero por agilizar la entrega lo dejo así
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comercio actualizado exitosamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/getMerchants")
    @ApiOperation(value = "Obtener todos los merchants")
    /*
    public List<MerchantOutputDTO> getAllMerchants() {

        List<MerchantGenericModel> res = merchantGetUseCase.getAllMerchants();

        List<MerchantOutputDTO> merchantOutputDTOList = res.stream()
                .map(merchantMappers::modelToOutput)
                .collect(Collectors.toList());

        return merchantOutputDTOList;

    }
    */
    public List<FullMerchantOutputDTO> getAllMerchants() {

        List<MerchantGenericModel> res = merchantGetUseCase.getAllMerchants();

        List<FullMerchantOutputDTO> merchantOutputDTOList = res.stream()
                .map(merchantMappers::modelToFullOutput)
                .collect(Collectors.toList());

        return merchantOutputDTOList;

    }

    @DeleteMapping("/deleteMerchant/{id}")
    @ApiOperation(value = "Eliminar un merchant")
    public void deleteMerchant(
            @ApiParam(value = "ID del merchant a eliminar", required = true)
            @PathVariable String id) {

        merchantDeleteUseCase.deleteMerchant(id);

    }

}

