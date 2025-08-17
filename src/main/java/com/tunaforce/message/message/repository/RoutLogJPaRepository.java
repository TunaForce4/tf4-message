package com.tunaforce.message.message.repository;

import com.tunaforce.message.message.entity.DeliveryRouteLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoutLogJPaRepository extends JpaRepository<DeliveryRouteLog,UUID> {

    @Query("SELECT D FROM DeliveryRouteLog D WHERE D.deliveryId =  :deliveryId")
    Optional<List<DeliveryRouteLog>> findDeliveryRouteLogByDeliveryId(UUID deliveryId);
}
