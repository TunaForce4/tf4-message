package com.tunaforce.message.message.dto.response.hub;

public record HubResponseDto(
        Long Id,
        String hubName,
        String hubAddress,
        String latitude,
        String longitude
) {

}
