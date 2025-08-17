package com.tunaforce.message.config;


import org.springframework.data.domain.AuditorAware;


public class JpaConfig {


    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
