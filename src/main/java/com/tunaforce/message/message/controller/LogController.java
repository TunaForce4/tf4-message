package com.tunaforce.message.message.controller;

import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.message.dto.request.CreateRouteLogRequestDto;
import com.tunaforce.message.message.dto.response.SearchLogResponseDto;
import com.tunaforce.message.message.service.feignClient.ClientCoordinatesService;
import com.tunaforce.message.message.service.RoutLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "로그 CRUD")
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final RoutLogService routLogService;
    private final ClientCoordinatesService ccs;


    @GetMapping
    public ResponseEntity<ApiResponse<List<SearchLogResponseDto>>> getLogDatas() throws Exception {
            List<SearchLogResponseDto> tempList = routLogService.readAllLog();
            ApiResponse<List<SearchLogResponseDto>> resultDto = ApiResponse.success(tempList);
            return ResponseEntity.ok(resultDto);
    }

    //deliveryID 값으로 검색하여 해당 값을 받아 수정
    //수정되는 값은 실제 데이터의 update : real_distance, real_time, curr_status
    //실제 총 거리와 시간이 기입되면 배송 완료라고 가정한다.
    @PutMapping
    public ResponseEntity<ApiResponse<Boolean>> updateLogRealData(@RequestParam Long deliveryId ){
        Boolean tempList = routLogService.updateStatus(deliveryId);
        ApiResponse<Boolean> resultDto = ApiResponse.success(tempList);
        return ResponseEntity.ok(resultDto);
    }
    //deliveryID 값으로 검색하여 해당 값을 받아 삭제
    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteLogDatas(@RequestParam String deliveryId ){
        return ResponseEntity.ok(null);
    }

}
