package com.tunaforce.message.maps.dto.naverRoute;

import java.util.List;

public record Goal(
        int dir,
        List<Double> location
) {
}
