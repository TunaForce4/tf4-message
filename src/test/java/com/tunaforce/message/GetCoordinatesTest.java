package com.tunaforce.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunaforce.message.cmmn.RouteData;
import com.tunaforce.message.maps.dto.naverRoute.Route;
import com.tunaforce.message.maps.dto.naverRoute.Summary;
import com.tunaforce.message.maps.dto.naverRoute.direction5ResponseDto;
import com.tunaforce.message.maps.service.ClientCoordinatesData;
import com.tunaforce.message.maps.service.ClientRoutesData;
import com.tunaforce.message.maps.dto.naverMap.geocodeResponseDto;
import com.tunaforce.message.maps.service.mapInfoService;
import com.tunaforce.message.message.DirectMessageScheduler;
import com.tunaforce.message.message.dto.response.delivery.DeliveryForm;
import com.tunaforce.message.message.dto.response.delivery.GetDeliveriesResponseDto;
import com.tunaforce.message.message.dto.response.delivery.GetDeliverymenResponseDto;
import com.tunaforce.message.message.service.feignClient.ClientDeliveryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@SpringBootTest
public class GetCoordinatesTest {

    @Autowired
    private ClientCoordinatesData clientCoordinatesData;
    @Autowired
    private ClientRoutesData clientRoutesData;
    @Autowired
    private mapInfoService mapInfoService;


    @Test
    @DisplayName("경도 위도 데이터 수신 테스트")
    void getCoordinatesTest() throws Exception {

        String Id = "osh066qnrv";
        String Key = "uvvXHOeBUf3sEaIBaCX5jqBbJHBZqYNrem0s07lj";

        String result = clientCoordinatesData.getGeocode(
                Id,
                Key,
                "거창군 마리면 풍계1길 27-103"
        );
        ObjectMapper test = new ObjectMapper();
        geocodeResponseDto tester = test.readValue(result, geocodeResponseDto.class);

        String ss = tester.addresses.get(0).getX();
        //String resultsData = ccs.getCoordinates( Id, Key,"대구 비산동 320-9");
        System.out.println("ss = " + ss);
        System.out.println("resultsData = " + result);
    }

    @Test
    @DisplayName("루트 데이터 수신 테스트")
    void getRoute() throws JsonProcessingException {
        String Id = "osh066qnrv";
        String Key = "uvvXHOeBUf3sEaIBaCX5jqBbJHBZqYNrem0s07lj";

        String geochang = clientCoordinatesData.getGeocode(
                Id,
                Key,
                "거창군 마리면 풍계1길 27-103"
        );

        String daegu = clientCoordinatesData.getGeocode(
                Id,
                Key,
                "대구광역시 서구 비산동 320-9"
        );


        ObjectMapper test = new ObjectMapper();
        geocodeResponseDto geochangdto = test.readValue(geochang, geocodeResponseDto.class);
        geocodeResponseDto daegudto = test.readValue(daegu, geocodeResponseDto.class);

        String resultData = clientRoutesData.getRoute(
                Id,
                Key,
                daegudto.getAddresses().get(0).getX() + "," + daegudto.getAddresses().get(0).getY(),
                geochangdto.getAddresses().get(0).getX() + "," + geochangdto.getAddresses().get(0).getY()

        );


        System.out.println("resultData = " + resultData);
    }


    @Autowired
    mapInfoService mIService;

    @Test
    @DisplayName("루트 데이터2 테스트")
    void getRoute2() throws JsonProcessingException {
        RouteData routeData = new RouteData(
                "거창군 마리면 풍계1길 27-103",
                "대구광역시 서구 비산동 320-9"
        );


        String startStr = routeData.getStart();
        String endStr = routeData.getGoal();

        //DB에서 가져와야함
        String Id = "osh066qnrv";
        String Key = "uvvXHOeBUf3sEaIBaCX5jqBbJHBZqYNrem0s07lj";
        ;

        String result = clientCoordinatesData.getGeocode(
                Id,
                Key,
                routeData.getStart()
        );
        ObjectMapper dataParser = new ObjectMapper();

        geocodeResponseDto startCoordinate = dataParser.readValue(result, geocodeResponseDto.class);

        result = clientCoordinatesData.getGeocode(
                Id,
                Key,
                routeData.getGoal()
        );

        geocodeResponseDto endCoordinate = dataParser.readValue(result, geocodeResponseDto.class);


        String rowData = clientRoutesData.getRoute(
                Id,
                Key,
                startCoordinate.getPoints().get(0),
                endCoordinate.getPoints().get(0)
        );

        direction5ResponseDto direction5Response = dataParser.readValue(rowData, direction5ResponseDto.class);

        Summary tempRoute = direction5Response.getRoute().traoptimal().get(0).summary();

        //전체 경로 거리
        int distance = tempRoute.distance();
        //전체 경로 소요시간 (ms)
        int duration = tempRoute.duration();
        String routTime = mIService.convertMsToTimeString(duration);
        //예상도착일시 (yyyy-MM-dd HH:mm:ss)
        String departuretime = tempRoute.departureTime();


        System.out.println("resultData = " + rowData);
        Assertions.assertThat(rowData).isNotNull();
    }

    @Autowired
    ClientDeliveryService clientDeliveryService;

    @Test
    @DisplayName("허브 좌표 테스트")
    void getHubs() throws JsonProcessingException {


        DeliveryForm<GetDeliveriesResponseDto> tempData = clientDeliveryService.getListDelivery("2025-08-22");
//허브 별 담당자 조회
        //role로 구분
        DeliveryForm<GetDeliverymenResponseDto> resultAgents = clientDeliveryService.getListDeliverymen("Company");

        DirectMessageScheduler temp =  new DirectMessageScheduler();






    }

}
