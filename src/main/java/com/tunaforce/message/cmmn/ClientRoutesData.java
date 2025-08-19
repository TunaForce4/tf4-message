package com.tunaforce.message.cmmn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverRoute", url = "${Naver.API.Directions 5}")
public interface ClientRoutesData {
    @GetMapping("/driving")
    String getRoute(
            @RequestHeader("X-NCP-APIGW-API-KEY-ID") String clientId,
            @RequestHeader("X-NCP-APIGW-API-KEY") String clientSecret,
            @RequestParam String start,
            @RequestParam String goal
    );
}
