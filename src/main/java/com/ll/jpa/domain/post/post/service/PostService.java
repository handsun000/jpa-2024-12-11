package com.ll.jpa.domain.post.post.service;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(String title, String content) {
        Post post = Post
                .builder()
                .title("title")
                .content("content")
                .modifiedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .blind(false)
                .build();

        postRepository.save(post);
    }

    public long count() {
        return postRepository.count();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }
}
