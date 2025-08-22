package com.tunaforce.message.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.cmmn.RouteData;
import com.tunaforce.message.cmmn.SlackMsg;
import com.tunaforce.message.maps.dto.naverRoute.Summary;
import com.tunaforce.message.maps.dto.naverRoute.direction5ResponseDto;
import com.tunaforce.message.maps.service.mapInfoService;
import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import com.tunaforce.message.message.dto.response.MessageLogResponseDto;
import com.tunaforce.message.message.dto.response.delivery.DeliveryForm;
import com.tunaforce.message.message.dto.response.delivery.GetDeliveriesResponseDto;
import com.tunaforce.message.message.dto.response.delivery.GetDeliverymenResponseDto;
import com.tunaforce.message.message.dto.response.hub.HubResponseDto;
import com.tunaforce.message.message.dto.response.hub.HubsResponseDto;
import com.tunaforce.message.message.entity.DeliveryStatus;
import com.tunaforce.message.message.entity.MessageManagement;
import com.tunaforce.message.message.repository.MessageLogRepository;
import com.tunaforce.message.message.repository.StatusRepository;
import com.tunaforce.message.message.service.MessageService;
import com.tunaforce.message.message.service.feignClient.ClientDeliveryService;
import com.tunaforce.message.message.service.feignClient.ClientHubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DirectMessageScheduler {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ClientHubService clientHubService;
    @Autowired
    private ClientDeliveryService clientDeliveryService;
    @Autowired
    private mapInfoService mIService;
    @Autowired
    private MessageService msgApp;
    @Autowired
    private MessageLogRepository messageLogRepository;

    //@Scheduled(fixedRate = 5000)
    @Scheduled(cron = "${scheduler.second} ${scheduler.minute} ${scheduler.hour} * * *") // 매일 오전 6시 (초, 분, 시, 일, 월, 요일)
    public void directMessageScheduler() throws NoSuchElementException, IOException, SlackApiException {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String today = now.format(formatter);
        String nowTime = now.format(formattertime);

        //배송 리스트를 받아온다
        //배송 리스트에 대한 날짜로 받아옴
        DeliveryForm<GetDeliveriesResponseDto> resultProduct =  clientDeliveryService.getListDelivery(today);
        if (resultProduct.getData().isEmpty()) {
            return;
        }
        //허브 별 담당자 조회
        //role로 구분
        DeliveryForm<GetDeliverymenResponseDto> resultAgents = clientDeliveryService.getListDeliverymen("Company");
//
        //허브 아이디로 각각 허브에 속한 담당자 끼리 묶는다
        Map<String, List<GetDeliverymenResponseDto>> hubGrouped =
                resultAgents.getData().stream().collect(Collectors.groupingBy(GetDeliverymenResponseDto::getHubId));
        // 배송 물품을 허브 별로
        Map<String, List<GetDeliveriesResponseDto>> productsinHub =
                resultProduct.getData().stream().collect(Collectors.groupingBy(GetDeliveriesResponseDto::getArrivalHubId));

        //status db 검색
        //어제 마지막 값을 받아온다(배달원 순번 값)
        List<DeliveryStatus> deliveryStatus = statusRepository.findAll();


        for (String hubId : hubGrouped.keySet()) {
            List<GetDeliveriesResponseDto> temphubList = productsinHub.get(hubId);
            if (temphubList != null) {
                List<GetDeliverymenResponseDto> tempmenList = hubGrouped.get(hubId);
                int idx =0;
                //순번들을 별도로 받아 인덱스 값을 반환한다.
                for (GetDeliveriesResponseDto productId : temphubList) {

                    //차례로 경로 로그를 남긴다
                    //productId 안에 있는 허브주소와 업체 주소를 함수에 넣는다.

                    HubResponseDto temphubSingledto = clientHubService.getHub(productId.getArrivalHubId());

                    RouteData routePath = new RouteData(
                            temphubSingledto.getHubAddress(),
                            productId.getDeliveryAddress()
                    );

                    log.info("Search data : From. {}-> To. {}",routePath.getGoal(), routePath.getGoal());

                    direction5ResponseDto routeInfo = mIService.getRoute(routePath);

                    Summary tempRoute = routeInfo.getRoute().traoptimal().get(0).summary();


                    //전체 경로 거리
                    int distance = tempRoute.distance();
                    //전체 경로 소요시간 (ms)
                    int duration = tempRoute.duration();
                    String routTime = mIService.convertMsToTimeString(duration);
                    //예상도착일시 (yyyy-MM-dd HH:mm:ss)
                    String departuretime = tempRoute.departureTime();
                    log.info("Total route distance : {}", distance);
                    log.info("Total route time : {}", duration);
                    log.info("Total route prediction : {}", routTime);

                    GetDeliverymenResponseDto manSingle = tempmenList.get(idx++);

                    //위의 받은 값으로 각 담당자에서 메세지를 보낸다.
                    CreateMessageLogRequestDto msgInfo = new CreateMessageLogRequestDto(
                            "\n["+nowTime+"] -> \n"+"목적지 : " + routePath.getGoal(),
                            UUID.randomUUID(),
                            manSingle.getUserId()
                    );


                    MessageLogResponseDto tempData = msgApp.sendMessage(UUID.fromString("49195b81-db15-438c-8f1c-63b17739c027"), msgInfo);

                    //로그는 송신이 된 후에 쌓여야 함
                    MessageManagement messageManagement = new MessageManagement(tempData);
                    messageLogRepository.save(messageManagement);
                    //앱을 통한 메세지 송신 로직 구성



                }
            }


        }
    }

}




