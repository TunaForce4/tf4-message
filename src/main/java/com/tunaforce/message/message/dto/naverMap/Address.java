package com.tunaforce.message.message.dto.naverMap;

import java.util.List;

public record Address(
        String roadAddress,
        String jibunAddress,
        String englishAddress,
        List<AddressElement> addressElements,
        String x,
        String y,
        double distance
) {}
