package com.tunaforce.message.message.service.feignClient;

import com.tunaforce.message.cmmn.CoordinatesData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientCoordinatesService {
    private final CoordinatesData coordinatesData;

    public String getCoordinates(String clientId, String clientSecret, String address) throws Exception {
        String result = coordinatesData.getGeocode(
                clientId,
                clientSecret,
                address
        );
        log.info("the Data from Naver : " + result);
        // JSON 파싱 (Jackson 등 사용)
//        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
//        com.fasterxml.jackson.databind.JsonNode root = mapper.readTree(result);
//        if (root.path("addresses").isArray() && root.path("addresses").size() > 0) {
//            com.fasterxml.jackson.databind.JsonNode addr = root.path("addresses").get(0);
//            String x = addr.get("x").asText(); // 경도
//            String y = addr.get("y").asText(); // 위도
//            return new String[]{x, y};
//        }
//        return null;
        return result;
    }
}
