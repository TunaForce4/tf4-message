package com.tunaforce.message.message.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_routeLog")
public class DeliveryRouteLog extends Timestamped {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "deliveryIdx", updatable = false, nullable = false)
    private UUID deliveryIdx;
    @Column(name = "delivery_Id",  nullable = false)
    private UUID deliveryId;
    @Column(name = "hub_Id",  nullable = false)
    private UUID hubId;
    @Column(name = "company_Id",  nullable = false)
    private UUID companyId;
    @Column(name = "appx_distance",  nullable = false)
    private String appxDistance;
    @Column(name = "appx_Time",  nullable = false)
    private String appxTime;
    @Column(name = "real_distance",  nullable = false)
    private String realDistance;
    @Column(name = "real_Time",  nullable = false)
    private String realTime;
    @Column(name = "curr_status",  nullable = false)
    private String currstatus;

    //현재 택배 상태 수정
    public void updateStatus(){

    }
    //실제 소요 시간 수정
    public void updateRealInfo(){

    }

    public void deleteLog(UUID deliveryId){
        delete(deliveryId);
    }

}
