package com.tunaforce.message.message.dto.naverRoute;

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
