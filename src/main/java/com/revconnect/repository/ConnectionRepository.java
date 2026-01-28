package com.revconnect.repository;

import com.revconnect.models.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    Optional<Connection> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
    List<Connection> findByReceiverIdAndStatus(Long receiverId, String status);
    List<Connection> findBySenderIdAndStatusOrReceiverIdAndStatus(
            Long senderId, String status1, Long receiverId, String status2);
    boolean existsBySenderIdAndReceiverId(Long senderId, Long receiverId);
}