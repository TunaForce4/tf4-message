package com.tunaforce.message.maps.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.maps.dto.response.CoordinatesDataResponseDto;
import com.tunaforce.message.maps.service.mapInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps")
@RequiredArgsConstructor
public class mapInfoController {

    private final mapInfoService mapInfoService;

    //좌표 반환 API
    @GetMapping("/Coordinates")
    public ResponseEntity<ApiResponse<CoordinatesDataResponseDto>> getCoords(@RequestParam String address) throws JsonProcessingException {
        CoordinatesDataResponseDto resultDto = mapInfoService.getCoords(address);
        ApiResponse<CoordinatesDataResponseDto> resultResponse = ApiResponse.success(resultDto);
        return ResponseEntity.ok(resultResponse);

    }


    //경로 추적 - 예상 거리와 예상 시간 봔환

}
