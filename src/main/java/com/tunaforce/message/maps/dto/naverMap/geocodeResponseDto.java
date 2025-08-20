package com.tunaforce.message.maps.dto.naverMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class geocodeResponseDto {
    public String status;
    public Meta meta;
    public List<Address> addresses;
    public String errorMessage;

    public List<String> getPoints() {

        List<String> points = new ArrayList<>();

        for (Address address : this.addresses) {
            points.add(address.getX()+","+ address.getY());
        }

        return points;
    };
}
