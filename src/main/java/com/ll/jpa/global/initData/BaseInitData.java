package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.domain.post.postComment.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final PostCommentService postCommentService;

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            if (postService.count() > 0) return;

            Post post1 = postService.write("title1", "content1");
            Post post2 = postService.write("title2", "content2");
            Post post3 = postService.write("title3", "content3");

            PostComment comment1 = postCommentService.write(post1, "comment1");
            PostComment comment2 = postCommentService.write(post2, "comment2");
            PostComment comment3 = postCommentService.write(post3, "comment3");

            Post post = comment1.getPost();
        };
    }
}
