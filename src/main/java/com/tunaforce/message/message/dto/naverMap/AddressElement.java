package com.tunaforce.message.message.dto.naverMap;

import java.util.List;

public record AddressElement(
        List<String> types,
        String longName,
        String shortName,
        String code
) {}
