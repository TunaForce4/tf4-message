package com.tunaforce.message.message.service;


import com.tunaforce.message.message.dto.response.GetDeliveriesResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="delivery-service", url="${DeliveryFeign.url}")
public interface ClientDeliveryService {
    @GetMapping("/deliverys")
    public List<GetDeliveriesResponseDto> getListDelivery();
}
