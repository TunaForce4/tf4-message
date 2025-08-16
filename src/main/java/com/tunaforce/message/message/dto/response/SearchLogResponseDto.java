package com.tunaforce.message.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchLogResponseDto {
    private String hubId;
    private String deliveryId;
    private String CompanyId;
    private String realDistance;
    private String realTime;
    private String currStatus;
}
