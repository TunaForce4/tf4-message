package com.tunaforce.message.message.repository;

import com.tunaforce.message.message.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<DeliveryStatus, Integer> {

}
