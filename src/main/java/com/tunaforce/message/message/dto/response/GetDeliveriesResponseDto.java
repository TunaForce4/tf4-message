package com.tunaforce.message.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetDeliveriesResponseDto {
    private Long delivery_id;
    private String status;
    private Long departure_hub_id;
    private Long arrival_hub_id;
    private String delivery_address;
    private UUID received_userid;
    private String received_slackid;
    private Long agent_id;
    private Long user_id;
    private UUID order_id;
}
