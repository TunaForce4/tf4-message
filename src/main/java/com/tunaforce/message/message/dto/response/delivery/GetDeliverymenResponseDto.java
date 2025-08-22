package com.tunaforce.message.message.dto.response.delivery;

import com.tunaforce.message.message.entity.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class GetDeliverymenResponseDto extends Timestamped {
    private UUID userId;
    private String deliveryType;
    private Long deliverySeq;
    private String hubId;
    private String slackId;
}
