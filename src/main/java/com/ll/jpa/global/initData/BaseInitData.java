package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    @Order(1)
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            if (postService.count() > 0) return;

            postService.write("title1", "content1");
            postService.write("title2", "content2");
            postService.write("title3", "content3");
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner baseInitDataApplicationRunner2() {
        return args -> {
            Post post = postService.findById(1).get();

            System.out.println(post);
        };
    }
}
