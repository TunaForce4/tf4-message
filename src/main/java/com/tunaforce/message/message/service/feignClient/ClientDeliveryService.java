package com.tunaforce.message.message.service.feignClient;


import com.tunaforce.message.message.dto.response.delivery.DeliveryForm;
import com.tunaforce.message.message.dto.response.delivery.GetDeliveriesResponseDto;
import com.tunaforce.message.message.dto.response.delivery.GetDeliverymenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(name="delivery-service", url="${Clients.Delivery}")
public interface ClientDeliveryService {
    //물품 배송에 대한 리스트 - 금일자로 된 리스트 할당을 받아야함
    @GetMapping("/deliveries")
    DeliveryForm<GetDeliveriesResponseDto> getListDelivery(@RequestParam("q") String p);

    //업체 배송 담당자 조건으로 쿼리
    @GetMapping("/delivery-agents")
    DeliveryForm<GetDeliverymenResponseDto> getListDeliverymen(@RequestParam("q") String role);
//http://localhost:3370/delivery-agents?q=hub

    //업체 배송 담당자 배정 후 배송 테이블에 업체 배송 담당자 ID 업데이트
//    @PostMapping("/deliveries")

}
