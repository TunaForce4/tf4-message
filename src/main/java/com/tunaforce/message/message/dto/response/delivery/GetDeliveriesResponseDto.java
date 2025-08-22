package com.tunaforce.message.message.dto.response.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetDeliveriesResponseDto {
    private String deliveryId;
    private String orderId;
    private String status;
    private String departureHubId;
    private String arrivalHubId;
    private String deliveryAddress;
    private String receivedUserId;
    private String receivedSlackId;
    private String companyDeliveryAgentId;
    private String createdAt;
    private String updatedAt;
}