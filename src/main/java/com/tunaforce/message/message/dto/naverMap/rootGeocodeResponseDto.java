package com.tunaforce.message.message.dto.naverMap;

//가장 기본 클래스

import java.util.List;

public record rootGeocodeResponseDto(
        String status,
        Meta meta,
        List<Address> addresses,
        String errorMessage
) {}

