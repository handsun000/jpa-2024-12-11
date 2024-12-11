package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            postService.write("title1", "content1");
            postService.write("title2", "content2");
            postService.write("title3", "content3");
        };
    }
}
