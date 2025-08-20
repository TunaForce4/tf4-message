package com.tunaforce.message.maps.dto.naverMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressElement {
    private List<String> types;
    private String longName;
    private String shortName;
    private String code;
}
