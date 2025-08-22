package com.tunaforce.message.maps.dto.naverMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
