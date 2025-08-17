package com.tunaforce.message.message.service;


import com.tunaforce.message.message.dto.response.GetDeliveriesResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient(name="delivery-service", url="${DeliveryFeign.url}")
public interface ClientDeliveryService {
    @GetMapping("/deliverys")
    public List<GetDeliveriesResponseDto> getListDelivery();
}
