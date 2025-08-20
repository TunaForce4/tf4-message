package com.tunaforce.message.message.dto.response.hub;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HubsResponseDto {
    List<HubResponseDto> content;
}
