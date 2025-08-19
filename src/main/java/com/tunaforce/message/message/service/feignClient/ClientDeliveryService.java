package com.tunaforce.message.message.service.feignClient;


import com.tunaforce.message.message.dto.response.GetDeliveriesResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient(name="delivery-service", url="${DeliveryFeign.url}")
public interface ClientDeliveryService {
    @GetMapping("/deliverys")
    List<GetDeliveriesResponseDto> getListDelivery();
    //업체 배송 담당자 조건으로 쿼리


    //업체 배송 담당자 배정 후 배송 테이블에 업체 배송 담당자 ID 업데이트
}
