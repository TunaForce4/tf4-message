package com.tunaforce.message.message.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "p_delivery_status")
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryStatus extends Timestamped {
    @Id
    private UUID userId;
    @Column(length = 100)
    private String hubid;
    private Long deliverySeq;
}
