package com.tunaforce.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunaforce.message.cmmn.ClientCoordinatesData;
import com.tunaforce.message.cmmn.ClientRoutesData;
import com.tunaforce.message.message.dto.naverMap.rootGeocodeResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootTest
public class GetCoordinatesTest {

    @Autowired
    private ClientCoordinatesData clientCoordinatesData;
    @Autowired
    private ClientRoutesData clientRoutesData;



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
        rootGeocodeResponseDto tester = test.readValue(result, rootGeocodeResponseDto.class);


        //String resultsData = ccs.getCoordinates( Id, Key,"대구 비산동 320-9");
        System.out.println("resultsData = " + result);
    }

    @Test
    @DisplayName("루트 데이터 수신 테스트")
    void getRouteTest() throws JsonProcessingException {

        ObjectMapper test = new ObjectMapper();

        String Id = "osh066qnrv";
        String Key = "uvvXHOeBUf3sEaIBaCX5jqBbJHBZqYNrem0s07lj";

        String start = clientCoordinatesData.getGeocode(
                Id,
                Key,
                "대구광역시 서구 비산동 320-9"
        );

        rootGeocodeResponseDto startPt = test.readValue(start, rootGeocodeResponseDto.class);

        String goal = clientCoordinatesData.getGeocode(
                Id,
                Key,
                "거창군 마리면 풍계1길 27-103"
        );
        rootGeocodeResponseDto goalPt = test.readValue(goal, rootGeocodeResponseDto.class);

        String result = clientRoutesData.getRoute(
                Id,
                Key,
                startPt.addresses().get(0).x()+","+startPt.addresses().get(0).y(),
                goalPt.addresses().get(0).x()+","+goalPt.addresses().get(0).y()
        );

        System.out.println("result = " + result);
    }

}
