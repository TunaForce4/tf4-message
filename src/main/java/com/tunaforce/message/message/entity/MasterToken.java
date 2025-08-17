package com.tunaforce.message.message.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "p_master_tokens")
@NoArgsConstructor
@AllArgsConstructor
public class MasterToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 150, name = "map_id")
    private String mapAuthId;
    @Column(length = 150, name = "map_key")
    private String mapAuthKey;
    @Column(length = 150, name = "message_token")
    private String messageAppToken;
}
