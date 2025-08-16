package com.tunaforce.message.message.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "NaverMap", url = " https://naveropenapi.apigw.ntruss.com/map-direction/v1")
public interface ClientNaverApi {
    @GetMapping("/driving")


}
