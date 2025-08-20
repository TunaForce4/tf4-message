package com.tunaforce.message.maps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunaforce.message.maps.dto.response.CoordinatesDataResponseDto;
import com.tunaforce.message.token.entity.MasterToken;
import com.tunaforce.message.maps.repository.mapInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class mapInfoService {

    private final ClientCoordinatesData clientCoordinatesData;
    private final mapInfoRepository mapInfoRepository;

    // 경도 위도 값 반환 부
    public CoordinatesDataResponseDto getCoords(String address) throws JsonProcessingException {
        MasterToken masterToken = mapInfoRepository.getMasterToken().orElseThrow(
                () -> new NoSuchElementException("MasterTokens not found")
        );
        //DB에서 가져와야함
        String Id = masterToken.getMapId();
        String Key = masterToken.getMapKey();

        String result = clientCoordinatesData.getGeocode(
                Id,
                Key,
                address
        );
        ObjectMapper dataParser = new ObjectMapper();

        return dataParser.readValue(result, CoordinatesDataResponseDto.class);


    }
}
