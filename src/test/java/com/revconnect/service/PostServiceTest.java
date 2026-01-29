package com.revconnect.service;

import com.revconnect.models.Post;
import com.revconnect.repository.PostRepository;
import com.revconnect.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    private static final Logger logger = LogManager.getLogger(PostServiceTest.class);

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    // ---------- Test createPost ----------
    @Test
    void testCreatePost() {
        logger.info("testCreatePost started");

        Long userId = 1L;
        String content = "Hello World";

        when(userRepository.existsById(userId)).thenReturn(true);
        when(postRepository.save(any(Post.class))).thenAnswer(i -> {
            Post p = i.getArgument(0);
            p.setId(1L); // Mock ID after save
            return p;
        });

        Post saved = postService.createPost(userId, content);

        assertNotNull(saved.getId());
        assertEquals(userId, saved.getUserId());
        assertEquals(content, saved.getContent());

        logger.info("testCreatePost success");
    }

    // ---------- Test getPostsByUserId ----------
    @Test
    void testGetPostsByUserId() {
        logger.info("testGetPostsByUserId started");

        Long userId = 1L;
        Post mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setUserId(userId);
        mockPost.setContent("Hello World");

        List<Post> mockPosts = List.of(mockPost);
        when(postRepository.findByUserId(userId)).thenReturn(mockPosts);

        List<Post> posts = postService.getPostsByUserId(userId);

        assertEquals(1, posts.size());
        assertEquals("Hello World", posts.get(0).getContent());

        logger.info("testGetPostsByUserId success");
    }

    // ---------- Test deletePost ----------
    @Test
    void testDeletePost() {
        logger.info("testDeletePost started");

        Long postId = 1L;
        Long userId = 1L;

        when(postRepository.deleteByIdAndUserId(postId, userId)).thenReturn(1);

        boolean deleted = postService.deletePost(postId, userId);

        assertTrue(deleted);

        logger.info("testDeletePost success");
    }
}
