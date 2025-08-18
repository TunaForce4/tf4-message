package com.tunaforce.message;

import com.tunaforce.message.cmmn.CoordinatesData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootTest
public class GetCoordinatesTest {

    @Autowired
    private CoordinatesData coordinatesData;



    @Test
    @DisplayName("경도 위도 데이터 수신 테스트")
    void getCoordinatesTest() throws Exception {

        String Id = "osh066qnrv";
        String Key = "uvvXHOeBUf3sEaIBaCX5jqBbJHBZqYNrem0s07lj";

        String result = coordinatesData.getGeocode(
                Id,
                Key,
                "대구 비산동 320-9"
        );


        //String resultsData = ccs.getCoordinates( Id, Key,"대구 비산동 320-9");
        System.out.println("resultsData = " + result);
    }
}
