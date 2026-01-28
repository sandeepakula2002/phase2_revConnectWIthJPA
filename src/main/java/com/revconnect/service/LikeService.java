package com.revconnect.service;

import com.revconnect.models.Like;
import com.revconnect.repository.LikeRepository;
import com.revconnect.repository.PostRepository;
import com.revconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Like likePost(Long userId, Long postId) {
        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new RuntimeException("Already liked");
        }

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found");
        }

        Like like = new Like();
        like.setUserId(userId);
        like.setPostId(postId);
        return likeRepository.save(like);
    }

    public boolean unlikePost(Long userId, Long postId) {
        return likeRepository.deleteByUserIdAndPostId(userId, postId) > 0;
    }

    public int getLikeCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}