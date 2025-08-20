package com.tunaforce.message.message.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MapKeyRequestDto {
    @NotNull(message = "userId가 누락 되었습니다.")
    private UUID userId;
    private String mapId;
    private String mapkey;
    private String messageToken;
}
