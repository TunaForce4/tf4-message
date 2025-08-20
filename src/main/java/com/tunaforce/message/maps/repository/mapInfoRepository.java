package com.tunaforce.message.maps.repository;

import com.tunaforce.message.token.entity.MasterToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface mapInfoRepository extends JpaRepository<MasterToken, UUID> {
    @Query("select m from MasterToken m where m.deletedAt != null ")
    Optional<MasterToken> getMasterToken();
}
