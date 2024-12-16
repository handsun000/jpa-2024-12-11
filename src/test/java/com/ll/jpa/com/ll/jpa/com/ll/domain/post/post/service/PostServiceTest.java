package com.ll.jpa.com.ll.jpa.com.ll.domain.post.post.service;

import com.ll.jpa.domain.post.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("글 2개 생성")
    @Transactional
    @Rollback
    void t1() {
        postService.write("제목1", "내용1");
        postService.write("제목2", "내용2");
    }
}