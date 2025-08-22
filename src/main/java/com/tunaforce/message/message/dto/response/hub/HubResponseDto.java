package com.tunaforce.message.message.dto.response.hub;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HubResponseDto{
    private Long Id;
    private String hubName;
    private String hubAddress;
    private String latitude;
    private String longitude;

}
