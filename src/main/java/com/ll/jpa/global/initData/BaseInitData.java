package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.domain.post.postComment.service.PostCommentService;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final PostCommentService postCommentService;
    @Autowired
    @Lazy
    private BaseInitData self;

    @Bean
    @Order(1)
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.work1();
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner baseInitData2ApplicationRunner() {
        return args -> self.work2();
    }

    @Transactional
    public void work1() {
        if (postService.count() > 0) return;

        Post post1 = postService.write("title1", "content1");
        Post post2 = postService.write("title2", "content2");
        Post post3 = postService.write("title3", "content3");

        PostComment postComment1 = postCommentService.write(post1, "comment1");
        PostComment postComment2 = postCommentService.write(post1, "comment2");
        PostComment postComment3 = postCommentService.write(post2, "comment3");
    }

    @Transactional
    public void work2() {
        PostComment postComment3 = postCommentService.findById(3).get();
        /*
        SELECT PC.*
        FROM post_comment AS PC
        WHERE PC.id = 3
        */

        Post postOfComment3 = postComment3.getPost();
        System.out.println("postOfComment3.id = " + postOfComment3.getId());
        System.out.println("postOfComment3.title = " + postOfComment3.getTitle());
        /*
        SELECT P.*
        FROM post AS P
        WHERE P.id = 2
        */

        System.out.println("postOfComment3.content = " + postOfComment3.getContent());
    }
}
