package com.tunaforce.message.message.entity;

import com.tunaforce.message.message.dto.request.MapKeyRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "p_master_tokens")
@NoArgsConstructor
@AllArgsConstructor
public class MasterToken extends Timestamped {
    //추후 리팩토링
    // 구조 변경 필요
    // 사용 앱 이름
    // 사용 헤더명 이름
    // 사용 키
    @Id
    private UUID userId;

    @Column(length = 150, name = "map_id")
    private String mapId;
    @Column(length = 150, name = "map_key")
    private String mapKey;
    @Column(length = 150, name = "message_token")
    private String messageAppToken;

    public MasterToken(MapKeyRequestDto mapKeyRequestDto) {
        this.userId = mapKeyRequestDto.getUserId();
        this.mapId = mapKeyRequestDto.getMapId();
        this.mapKey = mapKeyRequestDto.getMapkey();
        this.messageAppToken = mapKeyRequestDto.getMessageToken();

    }

    public void deleteTokens(UUID userId) {
        delete(userId);
    }
}
