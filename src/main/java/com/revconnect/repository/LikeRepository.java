package com.revconnect.repository;

import com.revconnect.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    int deleteByUserIdAndPostId(Long userId, Long postId);
    int countByPostId(Long postId);
}