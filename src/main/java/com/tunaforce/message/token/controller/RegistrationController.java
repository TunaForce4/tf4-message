package com.tunaforce.message.token.controller;

import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.message.dto.request.MapKeyRequestDto;
import com.tunaforce.message.message.dto.response.map.MapKeyReesponseDto;
import com.tunaforce.message.token.service.RegistrationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.logging.LoggingRebinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final LoggingRebinder loggingRebinder;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MapKeyReesponseDto>>> getAllTokens() {
        return ResponseEntity.ok(ApiResponse.success(registrationService.getAllTokens()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<MapKeyReesponseDto>> getTokens(@PathVariable UUID userId)
            throws NoResourceFoundException {
        MapKeyReesponseDto token = registrationService.getTokens(userId);
        return ResponseEntity.ok(ApiResponse.success(token));
    }



    //map관련 키 value 등록
    //userId 는 필수 값

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<String>> mapRegisterToken(
            @RequestBody @Valid MapKeyRequestDto mapKeyRequestDto,
            @RequestParam("X-User-Id") UUID userId,
            @RequestHeader("X-Roles")  String roles
    ) {
        log.info("{} has approached",userId);
        log.info("{} Roles",roles);

        if (roles.contains("MASTER")) {
            registrationService.createTokens(mapKeyRequestDto);
            ApiResponse<String> resultDto = ApiResponse.success("null");
            return ResponseEntity.ok(resultDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteToken(
            @RequestParam UUID userId
    ) throws NoResourceFoundException {
        registrationService.deleteTokens(userId);
        ApiResponse<String> resultDto = ApiResponse.success("null");
        return ResponseEntity.ok(resultDto);
    }


}
