package com.tunaforce.message.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


public class AuditorAwareImpl implements AuditorAware<UUID> {

    @NotNull
    @Override
    public Optional<UUID> getCurrentAuditor() {
        // TODO: 실제 서비스는 현재 로그인 사용자 ID 반환
//        return Optional.of("00000000-0000-0000-0000-000000000000");
        //return Optional.of(UUID.fromString("00000000-0000-0000-0000-000000000000")); // 임시 UUID
        return Optional.of(UUID.randomUUID());
    }
}
