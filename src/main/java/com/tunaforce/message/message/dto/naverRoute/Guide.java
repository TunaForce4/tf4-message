package com.tunaforce.message.message.dto.naverRoute;

public record Guide(
        int distance,
        int duration,
        String instructions,
        int pointIndex,
        int type
) {
}
