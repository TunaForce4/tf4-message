package com.tunaforce.message.message.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long userId;
    @Column(length = 150, name = "map_id")
    private String mapAuthId;
    @Column(length = 150, name = "map_key")
    private String mapAuthKey;
    @Column(length = 150, name = "message_token")
    private String messageAppToken;
}
