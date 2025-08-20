package com.tunaforce.message.maps.dto.naverMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class geocodeResponseDto {
    public String status;
    public Meta meta;
    public List<Address> addresses;
    public String errorMessage;

}
