package com.tunaforce.message.maps.dto.naverRoute;

public record Section(
        int congestion,
        int distance,
        String name,
        int pointCount,
        int pointIndex,
        int speed
) {
}
