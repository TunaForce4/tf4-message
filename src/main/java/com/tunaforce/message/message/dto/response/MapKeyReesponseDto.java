package com.tunaforce.message.message.dto.response;

import com.tunaforce.message.token.entity.MasterToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class MapKeyReesponseDto {
    private UUID userId;
    private String mapId;
    private String mapKey;
    private String messageAppToken;

    public MapKeyReesponseDto(MasterToken token) {
        this.userId = token.getUserId();
        this.mapId = token.getMapId();
        this.mapKey = token.getMapKey();
        this.messageAppToken = token.getMessageAppToken();
    }
}
