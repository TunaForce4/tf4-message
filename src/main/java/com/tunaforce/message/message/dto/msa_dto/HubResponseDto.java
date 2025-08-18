package com.tunaforce.message.message.dto.msa_dto;

public record HubResponseDto(
        Long Id,
        String hubName,
        String hubAddress,
        String latitude,
        String longitude
) {

}
