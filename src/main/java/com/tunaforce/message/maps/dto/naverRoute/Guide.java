package com.tunaforce.message.maps.dto.naverRoute;

public record Guide(
        int distance,
        int duration,
        String instructions,
        int pointIndex,
        int type
) {
}
