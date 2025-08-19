package com.tunaforce.message.message.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "p_delivery_status")
public class DeliveryStatus extends Timestamped {
    @Id
    private UUID userId;
    @Column(length = 100)
    private String hub_id;
}
