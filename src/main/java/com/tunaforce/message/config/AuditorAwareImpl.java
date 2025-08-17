package com.tunaforce.message.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //SecurityContext로 사용자 정보 반환
        return Optional.of(null);
    }
}
