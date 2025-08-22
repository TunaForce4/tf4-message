package com.tunaforce.message.maps.dto.naverRoute;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class direction5ResponseDto {
    private int code;
    private String message;
    private String currentDateTime;
    private Route route;
}

