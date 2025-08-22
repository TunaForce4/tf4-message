package com.tunaforce.message.message.dto.response.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tunaforce.message.message.entity.Timestamped;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetDeliverymenResponseDto extends Timestamped {
    private UUID userId;
    private String deliveryType;
    private Long deliverySeq;
    private String hubId;
    private String slackId;
}
