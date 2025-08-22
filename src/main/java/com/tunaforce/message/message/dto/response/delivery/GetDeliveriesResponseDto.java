package com.tunaforce.message.message.dto.response.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetDeliveriesResponseDto {
    private String deliveryId;
    private String orderId;
    private String status;
    private String departureHubId;
    private String arrivalHubId;
    private String deliveryAddress;
    private int receivedUserId;
    private String receivedSlackId;
    private int companyDeliveryAgentId;
    private String createdAt;
    private String updatedAt;
}
