package com.tunaforce.message.maps.dto.naverRoute;

import java.util.List;

public record Summary(
        List<List<Double>> bbox,
        String departureTime,
        int distance,
        int duration,
        int fuelPrice,
        Goal goal,
        Start start,
        int taxiFare,
        int tollFare
) {
}
