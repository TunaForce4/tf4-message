package com.tunaforce.message.message.repository;

import com.tunaforce.message.message.entity.MessageManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageLogRepository extends JpaRepository<MessageManagement, Long> {
}
