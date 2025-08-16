package com.tunaforce.message.cmmn;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NaverRouteAPI {
    //필수 필드만 추가
    // 출발지
    //출발지(경도,위도)
    //<예시>  start=127.12345,37.12345
    private String start;
    // 도착지
    //목적지(경도,위도)
    //':'로 구분하여 최대 10개의 목적지 입력 가능
    //입력한 목적지 중 가장 적은 비용으로 도달할 수 있는 목적지에 대한 경로 조회 <예시>  goal=123.45678,34.56789:124.56789,35.67890
    private String goal;
    //추후 리팩토링 시 추가
    //경유지
//    private String waypoints;
    //경로 조회 옵션
    //trafast |  tracomfort |  traoptimal (기본값) |  traavoidtoll |  traavoidcaronly
    //trafast : 실시간 빠른 길
    //tracomfort : 실시간 편한 길
    //traoptimal : 실시간 최적
    //traavoidtoll : 무료 우선
    //traavoidcaronly : 자동차 전용 도로 회피 우선
//    private String option;
    //차량 타입
    //1 (기본값) |  2 |  3 |  4 |  5 |  6
    //1 : 1종 소형차 2축 차량(윤폭 279.4 mm 이하 승용차, 소형 승합차, 소형 화물차)
    //2 : 2종 2축 차량(윤폭 279.4 mm 초과, 윤거 1,800 mm 이하 중형 승합차, 중형 화물차) 3 : 3종 대형차 2축 차량(윤폭 279.4 mm 초과, 윤거 1,800 mm 초과 대형 승합차, 2축 대형
    //화물차)
    //4 : 4종 3축 대형 화물차
    //5 : 5종 4축 이상 특수 화물차
    //6 : 1종 경형 자동차(배기량 1000 cc 미만으로 길이 3.6 m, 너비 1.6 m, 높이 2.0 m 이하)
//    private String cartype;
    //연료 타입
    //    asoline (기본값) | highgradegasoline | diesel | lpg
    //    gasoline : 휘발유
    //    highgradegasoline : 고급 휘발유
    //    diesel : 경유
    //    lpg : LPG
    //    유류비 계산에 활용
//    private String fueltype;

    public String returnQueryUri() {
        return "?start=" + this.start + "&goal=" + this.goal;
    }

}
