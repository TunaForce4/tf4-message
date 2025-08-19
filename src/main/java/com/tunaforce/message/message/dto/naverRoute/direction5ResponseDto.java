package com.tunaforce.message.message.dto.naverRoute;

public record direction5ResponseDto(
        int code,
        String message,
        String currentDateTime,
        Route route
) {}

