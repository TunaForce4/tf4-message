package com.tunaforce.message.message;


import com.tunaforce.message.message.dto.response.delivery.GetDeliveriesResponseDto;
import com.tunaforce.message.message.dto.response.delivery.GetDeliverymenResponseDto;
import com.tunaforce.message.message.dto.response.hub.HubsResponseDto;
import com.tunaforce.message.message.entity.DeliveryStatus;
import com.tunaforce.message.message.repository.StatusRepository;
import com.tunaforce.message.message.service.feignClient.ClientDeliveryService;
import com.tunaforce.message.message.service.feignClient.ClientHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component

public class DirectMessageScheduler {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ClientHubService  clientHubService;
    @Autowired
    private ClientDeliveryService clientDeliveryService;

    @Scheduled(cron = "${scheduler.second} ${scheduler.minute} ${scheduler.hour} * * *") // 매일 오전 6시 (초, 분, 시, 일, 월, 요일)
    public void directMessageScheduler() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = now.format(formatter);

        //배송 리스트를 받아온다
        //배송 리스트에 대한 날짜로 받아옴
        List<GetDeliveriesResponseDto> resultProduct =  clientDeliveryService.getListDelivery(today);
        //허브 별 담당자 조회
        //role로 구분
        List<GetDeliverymenResponseDto> resultAgents = clientDeliveryService.getListDeliverymen("Company");

        //허브 아이디로 각각 허브에 속한 담당자 끼리 묶는다
        Map<String, List<GetDeliverymenResponseDto>> hubGrouped =
                resultAgents.stream().collect(Collectors.groupingBy(GetDeliverymenResponseDto::getHubId));
        // 배송 물품을 허브 별로
        Map<String, List<GetDeliveriesResponseDto>> productsinHub =
                resultProduct.stream().collect(Collectors.groupingBy(GetDeliveriesResponseDto::getArrivalHubId));

        //status db 검색
        //어제 마지막 값을 받아온다(배달원 순번 값)
        List<DeliveryStatus>  deliveryStatus = statusRepository.findAll();



        for (String hubId : hubGrouped.keySet()) {
            List<GetDeliveriesResponseDto> temphubList = productsinHub.get(hubId);
            List<GetDeliverymenResponseDto> tempmenList = hubGrouped.get(hubId);
            //순번들을 별도로 받아 인덱스 값을 반환한다.
            List<Long> tempMenNo = tempmenList.stream()
                    .map(GetDeliverymenResponseDto::getDeliverySeq)
                    .toList();

            Optional<DeliveryStatus> lastTurns = deliveryStatus.stream()
                    .filter(ds -> hubId.equals(ds.getHubid()))
                    .findFirst();
            Long tempidx = lastTurns.get().getDeliverySeq();
            for (GetDeliveriesResponseDto productId : temphubList) {
                int idx = tempMenNo.indexOf(tempidx);
                GetDeliverymenResponseDto tempMen = tempmenList.get(++idx);

            }

        }

        //hub 리스트를 받아온다
        HubsResponseDto resultHubs = clientHubService.getHubs(0,0);





        for (GetDeliveriesResponseDto product : resultProduct) {
            //출발 허브
            product.getArrivalHubId();
        }





        //금일 예정 되어 있는 배송들 리스트


        //배송인원들 리스트


    }
}




