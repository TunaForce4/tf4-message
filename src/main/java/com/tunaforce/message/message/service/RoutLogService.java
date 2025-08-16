package com.tunaforce.message.message.service;


import com.tunaforce.message.message.dto.response.SearchLogResponseDto;
import com.tunaforce.message.message.entity.DeliveryRouteLog;
import com.tunaforce.message.message.repository.RoutLogJPaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutLogService {
    private final RoutLogJPaRepository routLogJPaRepository;

    public List<SearchLogResponseDto> readAllLog() {
        List<DeliveryRouteLog> results = routLogJPaRepository.findAll();
        return results.stream()
                .map(entity -> SearchLogResponseDto.builder()
                        .hubId(String.valueOf(entity.getHubId()))
                        .deliveryId(String.valueOf(entity.getDeliveryId()))
                        .CompanyId(String.valueOf(entity.getCompanyId()))
                        .realDistance(String.valueOf(entity.getRealDistance()))
                        .realTime(String.valueOf(entity.getRealTime()))
                        .currStatus(String.valueOf(entity.getCurrstatus()))
                        .build()
                ).collect(Collectors.toList());
    }

    public boolean updateStatus(Long deliveryId){
        return true;
    }

    public boolean deleteLog(Long deliveryId){
        DeliveryRouteLog deliveryRouteLog = routLogJPaRepository.findById(deliveryId).orElseThrow(() ->
                new NoSuchElementException("데이터가 존재하지 않습니다."));

        deliveryRouteLog.deleteLog(deliveryRouteLog.getDeliveryIdx());
        return true;
    }
}
