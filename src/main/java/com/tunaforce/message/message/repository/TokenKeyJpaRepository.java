package com.tunaforce.message.message.repository;

import com.tunaforce.message.message.entity.MasterToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TokenKeyJpaRepository extends JpaRepository<MasterToken, UUID> {

    @Query("SELECT M FROM MasterToken M WHERE M.deletedAt != null")
    List<MasterToken> findByDeletedAt();

}
