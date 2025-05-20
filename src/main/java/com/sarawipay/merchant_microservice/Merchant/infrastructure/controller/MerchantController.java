package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller;

import com.sarawipay.merchant_microservice.Merchant.application.MerchantGenericModel;
import com.sarawipay.merchant_microservice.Merchant.application.MerchantGetUseCaseImpl;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantAddUseCase;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantDeleteUseCase;
import com.sarawipay.merchant_microservice.Merchant.application.port.MerchantUpdateUseCase;
import com.sarawipay.merchant_microservice.Merchant.domain.GenericResponseEntity;
import com.sarawipay.merchant_microservice.Merchant.domain.mappers.MerchantMappers;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.IdInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantInputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input.MerchantUpdateRequestDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.FullMerchantOutputDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantIdDTO;
import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantOutputDTO;


import com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.output.MerchantTypesDTO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

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
    public FullMerchantOutputDTO addMerchant(

            @ApiParam(value = "Datos del merchant a crear", required = true)
            @Valid @RequestBody MerchantInputDTO merchantInputDTO) {

        MerchantGenericModel generic = merchantMappers.inputToModel(merchantInputDTO);

        return merchantMappers.modelToFullOutput(merchantAddUseCase.addMerchant(generic));
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
    public FullMerchantOutputDTO update(

            @ApiParam(value = "Datos del merchant a actualizar", required = true)
            @RequestBody MerchantUpdateRequestDTO merchantUpdate) {

        MerchantGenericModel generic = merchantMappers.updateRequestToModel(merchantUpdate);
        merchantUpdateUseCase.update(generic);


        return merchantMappers.modelToFullOutput(merchantUpdateUseCase.update(generic));
    }


    @GetMapping("/getMerchants")
    @ApiOperation(value = "Obtener todos los merchants")
    public List<FullMerchantOutputDTO> getAllMerchants() {

        List<MerchantGenericModel> res = merchantGetUseCase.getAllMerchants();

        List<FullMerchantOutputDTO> merchantOutputDTOList = res.stream()
                .map(merchantMappers::modelToFullOutput)
                .collect(Collectors.toList());

        return merchantOutputDTOList;

    }


    @DeleteMapping("/deleteMerchant/{id}")
    @ApiOperation(value = "Eliminar un merchant")
    public GenericResponseEntity<MerchantOutputDTO> deleteMerchant(
            @ApiParam(value = "ID del merchant a eliminar", required = true)
            @PathVariable String id) {

        MerchantOutputDTO deletedMerchant = merchantMappers.modelToOutput(
                merchantDeleteUseCase.delete(id)
        );

        return new GenericResponseEntity<>(
                "Merchant borrado con éxito.",
                String.valueOf(HttpStatus.NO_CONTENT.value()),
                deletedMerchant
        );
    }

    /*
    @DeleteMapping("/deleteMerchant/{id}")
    @ApiOperation(value = "Eliminar un merchant")
    public GenericResponseEntity<MerchantOutputDTO> deleteMerchant(
            @ApiParam(value = "ID del merchant a eliminar", required = true)
            @PathVariable String id) {


        MerchantOutputDTO deletedMerchant = merchantMappers.modelToOutput(merchantDeleteUseCase.delete(id));

        if (deletedMerchant != null) {
            return new GenericResponseEntity<MerchantOutputDTO>(
                    "Merchant borrado con éxito.",
                    String.valueOf(HttpStatus.NO_CONTENT.value()),
                    deletedMerchant
            );
        } else {
            return new GenericResponseEntity<MerchantOutputDTO>(
                    "Merchant no encontrado.",
                    String.valueOf(HttpStatus.NOT_FOUND.value()),
                    null

            );
        }
    }
    */


    @GetMapping("/getByClientId")
    @ApiOperation(value = "Obtener todos los merchants de un cliente")
    public List<FullMerchantOutputDTO> getMerchantsByClientId(
            @ApiParam(value = "ID del cliente", required = true)
            @RequestBody IdInputDTO idInputDTO) {

        List<MerchantGenericModel> res = merchantGetUseCase.findMerchantsByClientId(idInputDTO.getId());

        List<FullMerchantOutputDTO> merchantOutputDTOList = res.stream()
                .map(merchantMappers::modelToFullOutput)
                .collect(Collectors.toList());

        return merchantOutputDTOList;

    }

    // TODO: Hacer un EP para devolver todos los tipos de merchant para refinar las partes involucradas en front


    @GetMapping("/types")
    public GenericResponseEntity<MerchantTypesDTO> getAllMerchantTypes() {

        return new GenericResponseEntity<>(
                "Listado de merchant obtenido con éxito.",
                String.valueOf(HttpStatus.OK),
                merchantGetUseCase.getAllMerchantTypes()
        );
    }

}

