package com.tunaforce.message.message.dto.naverRoute;

import java.util.List;

public record Goal(
        int dir,
        List<Double> location
) {
}
