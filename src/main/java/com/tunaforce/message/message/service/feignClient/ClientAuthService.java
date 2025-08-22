package com.tunaforce.message.message.service.feignClient;


import com.tunaforce.message.message.dto.response.auth.UserInformationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "auth-Service", url = "${Clients.Auth}")
public interface ClientAuthService {
    //시용자 정보의 앱 ID가 필요
    @GetMapping("/users/{user_id}")
    UserInformationResponseDto getUserInfo(@PathVariable("user_id") UUID userId);

}
