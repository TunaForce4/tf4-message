package com.tunaforce.message.message.dto.naverRoute;

import java.util.List;

public record Traoptimal(
        List<Guide> guide,
        List<List<Double>> path,
        List<Section> section,
        Summary summary
) {
}
