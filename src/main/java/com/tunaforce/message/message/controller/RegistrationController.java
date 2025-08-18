package com.tunaforce.message.message.controller;

import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.message.dto.request.MapKeyRequestDto;
import com.tunaforce.message.message.dto.response.MapKeyReesponseDto;
import com.tunaforce.message.message.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

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
    public ResponseEntity<ApiResponse<String>> mapRegisterToken(
            @RequestBody @Valid MapKeyRequestDto mapKeyRequestDto
    ) throws NoResourceFoundException {
        registrationService.updateTokens(mapKeyRequestDto);
        ApiResponse<String> resultDto = ApiResponse.success("null");
        return ResponseEntity.ok(resultDto);
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
