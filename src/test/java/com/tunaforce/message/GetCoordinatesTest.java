package com.tunaforce.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunaforce.message.maps.service.ClientCoordinatesData;
import com.tunaforce.message.maps.service.ClientRoutesData;
import com.tunaforce.message.maps.dto.naverMap.geocodeResponseDto;
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
        geocodeResponseDto tester = test.readValue(result, geocodeResponseDto.class);

        String ss= tester.addresses.get(0).getX();
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
                daegudto.getAddresses().get(0).getX()+","+daegudto.getAddresses().get(0).getY(),
                geochangdto.getAddresses().get(0).getX()+","+geochangdto.getAddresses().get(0).getY()

        );



        System.out.println("resultData = " + resultData);
    }

}
