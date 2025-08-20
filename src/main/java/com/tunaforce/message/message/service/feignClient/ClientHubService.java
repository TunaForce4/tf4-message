package com.tunaforce.message.message.service.feignClient;

import com.tunaforce.message.message.dto.response.hub.HubResponseDto;
import com.tunaforce.message.message.dto.response.hub.HubsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hub-Service", url = "${Clients.Hub}")
public interface ClientHubService {
    @GetMapping("/hubs/{hub_id}")
    HubResponseDto getHub(@PathVariable("hub_id") String hub_id);

    @GetMapping("/hubs")
    HubsResponseDto getHubs(
            @RequestParam Integer page,
            @RequestParam Integer size
    );

}
