package com.sarawipay.merchant_microservice.Merchant.infrastructure.controller.DTO.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantInputDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @NotBlank(message = "El tipo de comercio es obligatorio")
    private String merchantType;

    @NotBlank(message = "El cliente asignado es obligatorio")
    @JsonProperty("gIndexClient")
    private String gIndexClient;


}