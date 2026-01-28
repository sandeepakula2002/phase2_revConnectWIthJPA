package com.revconnect.service;

import com.revconnect.models.Post;
import com.revconnect.repository.PostRepository;
import com.revconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(Long userId, String content) {
        // Check if user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        // Create post with Long ID (matching your Post.java constructor)
        Post post = new Post();
        post.setContent(content);
        post.setUserId(userId);
        return postRepository.save(post);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public boolean deletePost(Long postId, Long userId) {
        return postRepository.deleteByIdAndUserId(postId, userId) > 0;
    }
}