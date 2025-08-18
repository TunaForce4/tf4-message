package com.tunaforce.message.message.service;


import com.tunaforce.message.message.dto.request.MapKeyRequestDto;
import com.tunaforce.message.message.dto.response.MapKeyReesponseDto;
import com.tunaforce.message.message.entity.MasterToken;
import com.tunaforce.message.message.repository.TokenKeyJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final TokenKeyJpaRepository tokenKeyJpaRepository;

    //앱 토큰 추가
    @Transactional
    public MapKeyReesponseDto updateTokens(MapKeyRequestDto mapKeyRequestDto)
            throws NoResourceFoundException {
        UUID userId = mapKeyRequestDto.getUserId();

        // 1. userId로 기존 엔티티 조회
        MasterToken token = tokenKeyJpaRepository.findById(userId).orElseThrow(
                ()-> new NoResourceFoundException(HttpMethod.GET,"해당 userId가 존재하지 않습니다.")
        );

        // 2. null이 아닌 필드만 업데이트
        if (mapKeyRequestDto.getMapId() != null) {
            token.setMapId(mapKeyRequestDto.getMapId());
        }
        if (mapKeyRequestDto.getMapkey() != null) {
            token.setMapKey(mapKeyRequestDto.getMapkey());
        }
        if (mapKeyRequestDto.getMessageToken() != null) {
            token.setMessageAppToken(mapKeyRequestDto.getMessageToken());
        }
        MapKeyReesponseDto resultDto = new MapKeyReesponseDto(token);
        // 3. 트랜잭션 커밋 시점에 자동 업데이트 됨
        return resultDto;
    }
    //앱 변경시에 논리적 DB 제거
    public void deleteTokens(UUID userId) throws NoResourceFoundException {
        MasterToken mT = tokenKeyJpaRepository.findById(userId).orElseThrow(
                ()-> new NoResourceFoundException(HttpMethod.GET,"해당 userId가 존재하지 않습니다.")
        );

        mT.delete(userId);
    }

    public MapKeyReesponseDto getTokens(UUID userId) throws NoResourceFoundException {
        MasterToken mT = tokenKeyJpaRepository.findById(userId).orElseThrow(
                ()-> new NoResourceFoundException(HttpMethod.GET,"Token not found"));

        return MapKeyReesponseDto.builder()
                .userId(mT.getUserId())
                .mapId(mT.getMapId())
                .mapKey(mT.getMapKey())
                .messageAppToken(mT.getMessageAppToken())
                .build();

    }

    public List<MapKeyReesponseDto> getAllTokens(){
        List<MasterToken> mT = tokenKeyJpaRepository.findAll();
        return mT.stream()
                .map(MapKeyReesponseDto::new)
                .toList();
    }
}
