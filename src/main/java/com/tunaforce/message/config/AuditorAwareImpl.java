package com.tunaforce.message.config;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;
import java.util.UUID;


public class AuditorAwareImpl implements AuditorAware<UUID> {

    @NotNull
    @Override
    public Optional<UUID> getCurrentAuditor() {
        try {


            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attr == null) {
                return Optional.of(UUID.fromString("49195b81-db15-438c-8f1c-63b17739c027")); // 웹요청 없음 (예: 스케줄러 등)
            }


            // check type & casting
            if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
                HttpServletRequest request = servletRequestAttributes.getRequest();
                String userId = request.getHeader("X-User-Id");

                if (userId != null && !userId.isEmpty()) {
                    return Optional.of(UUID.fromString(userId));
                }
            }

            return Optional.of(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        } catch (Exception ex) {
            return Optional.of(UUID.fromString("49195b81-db15-438c-8f1c-63b17739c027")); // 웹요청 없음 (예: 스케줄러 등)
        }
    }
}
