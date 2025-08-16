package com.tunaforce.message.message.dto.request;

import jakarta.persistence.Column;

import java.util.UUID;

public class CreateRouteLogRequestDto {
    private UUID deliveryIdx;
    //배송 ID
    private UUID deliveryId;
    //허브 ID : 배송 테이블 내에 주소가 포함이 되어 있지만 허브ID로 배송 소속 기사 검색을 위함
    private UUID hubId;
    //배송 목적지 ID
    private UUID companyId;
    //네이버API를 사용한 예상 거리 및 시간를 기록
    private String appxDistance;
    private String appxTime;
    //초기 테이블 생성 시 값은 배송 중으로 고정
    private String currstatus;
}
