package com.tunaforce.message.message.controller;

import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.message.dto.request.CreateRouteLogRequestDto;
import com.tunaforce.message.message.dto.response.SearchLogResponseDto;
import com.tunaforce.message.message.service.RoutLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "로그 CRUD")
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final RoutLogService routLogService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SearchLogResponseDto>>> getLogDatas(){

            List<SearchLogResponseDto> tempList = routLogService.readAllLog();
            ApiResponse<List<SearchLogResponseDto>> resultDto = ApiResponse.success(tempList);
            return ResponseEntity.ok(resultDto);
    }

    //해당 테이블의 생성 시점을 확인할 필요가 있음
    //1, 각 허브에 배송 물건이 도착 했을 경우 routelog 테이블의 생성이 필요함 - @다예(배송 MSA) 허브 도착 시에
    //2. 당일 배송 담당자를 할당 후 생성
    @PostMapping
    public ResponseEntity<ApiResponse<List<SearchLogResponseDto>>> createLogDatas(@RequestBody CreateRouteLogRequestDto routRequests){
        return ResponseEntity.ok(null);
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
