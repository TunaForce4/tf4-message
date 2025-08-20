package com.tunaforce.message.message;


import com.tunaforce.message.message.dto.response.hub.HubsResponseDto;
import com.tunaforce.message.message.entity.DeliveryStatus;
import com.tunaforce.message.message.repository.StatusRepository;
import com.tunaforce.message.message.service.feignClient.ClientHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

public class DirectMessageScheduler {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ClientHubService  clientHubService;

    @Scheduled(cron = "${scheduler.second} ${scheduler.minute} ${scheduler.hour} * * *") // 매일 오전 6시 (초, 분, 시, 일, 월, 요일)
    public void directMessageScheduler() {
        //hub 리스트를 받아온다
        HubsResponseDto resultHubs = clientHubService.getHubs(0,1);

        //허브 별 담당자 조회


        //status db 검색
        //어제 마지막 값을 받아온다(배달원 순번 값)
        DeliveryStatus  deliveryStatus = statusRepository.findAll().get(0);
        UUID turnNo = deliveryStatus.getUserId();

        //금일 예정 되어 있는 배송들 리스트


        //배송인원들 리스트


    }
}
