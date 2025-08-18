package com.tunaforce.message.message.service.feignClient;

import com.tunaforce.message.message.dto.msa_dto.HubResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-Service", url = "${Clients.Hub}")
public interface ClientHubService {
    @GetMapping("/hubs/{hub_id}")
    HubResponseDto getHub(@PathVariable("hub_id") String hub_id);

}
