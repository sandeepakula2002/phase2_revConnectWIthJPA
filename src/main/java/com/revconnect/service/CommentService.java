package com.revconnect.service;

import com.revconnect.models.Comment;
import com.revconnect.repository.CommentRepository;
import com.revconnect.repository.PostRepository;
import com.revconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Comment addComment(Long userId, Long postId, String text) {
        // Check if user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        // Check if post exists
        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found");
        }

        // Create comment with Long IDs (matching your Comment.java constructor)
        Comment comment = new Comment();
        comment.setText(text);
        comment.setUserId(userId);
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public boolean deleteComment(Long commentId, Long userId) {
        return commentRepository.deleteByIdAndUserId(commentId, userId) > 0;
    }
}