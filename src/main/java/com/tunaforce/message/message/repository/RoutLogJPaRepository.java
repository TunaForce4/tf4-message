package com.tunaforce.message.message.repository;

import com.tunaforce.message.message.entity.DeliveryRouteLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutLogJPaRepository extends JpaRepository<DeliveryRouteLog,Long> {
}
