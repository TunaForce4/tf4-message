package com.tunaforce.message.message.service;


import com.tunaforce.message.message.dto.response.UserInformationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-Service", url = "auth url 확인 후 기입")
public interface ClientAuthService {
    //시용자 정보의 앱 ID가 필요
    @GetMapping("/users/{user_id}")
    public UserInformationResponseDto getUserInfo(@PathVariable("user_id") Long userId);

}
