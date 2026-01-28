package com.revconnect.service;

import com.revconnect.models.Connection;
import com.revconnect.models.User;
import com.revconnect.repository.ConnectionRepository;
import com.revconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserRepository userRepository;

    public Connection sendRequest(Long senderId, Long receiverId) {
        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Cannot send request to yourself");
        }

        if (connectionRepository.existsBySenderIdAndReceiverId(senderId, receiverId)) {
            throw new RuntimeException("Connection request already exists");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Connection connection = new Connection(sender, receiver, "PENDING");
        return connectionRepository.save(connection);
    }

    public List<Connection> getPendingRequests(Long userId) {
        return connectionRepository.findByReceiverIdAndStatus(userId, "PENDING");
    }

    public Connection acceptRequest(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new RuntimeException("Connection not found"));

        connection.setStatus("ACCEPTED");
        return connectionRepository.save(connection);
    }

    public Connection rejectRequest(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new RuntimeException("Connection not found"));

        connection.setStatus("REJECTED");
        return connectionRepository.save(connection);
    }

    public List<Connection> getConnections(Long userId) {
        // Fixed: Using the correct repository method
        return connectionRepository.findBySenderIdAndStatusOrReceiverIdAndStatus(
                userId, "ACCEPTED", userId, "ACCEPTED");
    }
}