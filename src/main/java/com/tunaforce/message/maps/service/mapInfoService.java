package com.tunaforce.message.maps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunaforce.message.cmmn.RouteData;
import com.tunaforce.message.maps.dto.naverMap.geocodeResponseDto;
import com.tunaforce.message.maps.dto.naverRoute.direction5ResponseDto;
import com.tunaforce.message.maps.dto.response.CoordinatesDataResponseDto;
import com.tunaforce.message.token.entity.MasterToken;
import com.tunaforce.message.maps.repository.mapInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class mapInfoService {

    private final ClientCoordinatesData clientCoordinatesData;
    private final ClientRoutesData clientRoutesData;
    private final mapInfoRepository mapInfoRepository;

    // 경도 위도 값 반환 부
    public geocodeResponseDto getCoords(String address) throws JsonProcessingException {
        MasterToken masterToken = mapInfoRepository.getMasterToken().orElseThrow(
                () -> new NoSuchElementException("MasterTokens not found")
        );
        //DB에서 가져와야함
        String Id = masterToken.getMapId();
        String Key = masterToken.getMapKey();
        log.info("Search Keys -> \n {} : {}",Id, Key);
        String result = clientCoordinatesData.getGeocode(
                Id,
                Key,
                address
        );
        ObjectMapper dataParser = new ObjectMapper();

        return dataParser.readValue(result, geocodeResponseDto.class);


    }


    //허브에서 경위도가 자체
    public direction5ResponseDto getRoute(RouteData routeData) throws JsonProcessingException {
        log.info("get the long and lat data");
        //각 주소로 경위도를 반환한다.
        geocodeResponseDto startCoordinate = getCoords(routeData.getStart());
        geocodeResponseDto endCoordinate = getCoords(routeData.getGoal());
        //네이버 API 필요 ID KEY
        MasterToken masterToken = mapInfoRepository.getMasterToken().orElseThrow(
                () -> new NoSuchElementException("MasterTokens not found")
        );
        //DB에서 가져와야함
        String Id = masterToken.getMapId();
        String Key = masterToken.getMapKey();

        String rowData = clientRoutesData.getRoute(
                Id,
                Key,
                startCoordinate.getPoints().get(0),
                endCoordinate.getPoints().get(0)
        );

        ObjectMapper dataParser = new ObjectMapper();
        return dataParser.readValue(rowData, direction5ResponseDto.class);
    }

    // 시분초를 변환하는 함수
    public String convertMsToTimeString(long millis) {
        long hours = millis / (1000 * 60 * 60);
        long minutes = (millis / (1000 * 60)) % 60;
        long seconds = (millis / 1000) % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
