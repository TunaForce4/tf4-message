package com.tunaforce.message.message.controller;

import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.message.dto.response.SearchLogResponseDto;
import com.tunaforce.message.message.service.RoutLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
