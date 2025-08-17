package com.tunaforce.message;

import com.tunaforce.message.message.service.ClientCoordinatesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;


@SpringBootTest
//@AutoConfigureWireMock(port = 0) // 랜덤 포트 WireMock 구동
@EnableFeignClients
//@ActiveProfiles("test")
public class GetCoordinatesTest {

    @Autowired
    private ClientCoordinatesService ccs;

    @Test
    @DisplayName("경도 위도 데이터 수신 테스트")
    void getCoordinatesTest() throws Exception {

        String Id = "s6m0vmxur3";
        String Key = "9y18o5DVhbDAa9PqKdIs82WlzdhiNJIzuniTRYKT";

        var resultsData = ccs.getCoordinates( Id, Key,"대구 비산동 320-9");
        System.out.println("resultsData = " + Arrays.toString(resultsData));
    }
}
