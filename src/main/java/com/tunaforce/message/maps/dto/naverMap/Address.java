package com.tunaforce.message.maps.dto.naverMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String roadAddress;
    private String jibunAddress;
    private String englishAddress;
    private List<AddressElement> addressElements;
    private String x;
    private String y;
    private double distance;
}
