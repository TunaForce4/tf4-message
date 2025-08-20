package com.tunaforce.message.maps.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//네이버 페인 클라이언트 인터페이스
@FeignClient(name = "naverGeocode", url = "${Naver.API.Geocoding}")
public interface ClientCoordinatesData {
    @GetMapping("/geocode")
    String getGeocode(
            @RequestHeader("X-NCP-APIGW-API-KEY-ID") String clientId,
            @RequestHeader("X-NCP-APIGW-API-KEY") String clientSecret,
            @RequestParam("query") String address
    );
}
