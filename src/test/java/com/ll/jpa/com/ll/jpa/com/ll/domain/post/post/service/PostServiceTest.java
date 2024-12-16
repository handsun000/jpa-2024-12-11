package com.ll.jpa.com.ll.jpa.com.ll.domain.post.post.service;

import com.ll.jpa.domain.member.member.entity.Member;
import com.ll.jpa.domain.member.member.service.MemberService;
import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Port;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("글 2개 생성")
    @Transactional
    @Rollback
    void t1() {
        Member user1 = memberService.findByUsername("user1").get();
        postService.write(user1, "title1", "content1");
        postService.write(user1, "title2", "content2");
    }

    @Test
    @DisplayName("findAll")
    void t2() {
        List<Post> posts = postService.findAll();
        assertEquals(3, posts.size());
    }

    @Test
    @DisplayName("findById")
    void t3() {
        Post post = postService.findById(3).get();
        assertEquals("title3", post.getTitle());
    }

    @Test
    @DisplayName("findByTitle")
    void t4() {
        List<Post> posts = postService.findByTitle("title1");
        Post post = posts.getFirst();
        assertEquals("title1", post.getTitle());
    }
    @Test
    @DisplayName("findByTitleAndContent")
    void t5() {
        List<Post> posts = postService.findByTitleAndContent("title1","content1");
        Post post = posts.get(0);
        assertEquals("title1",post.getTitle());
    }
    @Test
    @DisplayName("findByTitleLike")
    void t6() {
        List<Post> posts = postService.findByTitleLike("title%");
        Post post = posts.get(0);
        assertEquals("title1",post.getTitle());
    }
    @Test
    @DisplayName("findByTitleLikeOrderByIdDesc")
    void t7() {
        List<Post> posts = postService.findByTitleLikeOrderByIdDesc("title%");
        Post post = posts.get(0);
        assertEquals(3, post.getId());
    }
    @Test
    @DisplayName("findByOrderByIdDesc")
    void t8() {
        List<Post> posts = postService.findByOrderByIdDesc();
        Post post = posts.get(0);
        assertEquals(3, post.getId());
    }
    @Test
    @DisplayName("findTop2ByTitleLikeOrderByIdDesc")
    void t9() {
        List<Post> posts = postService.findTop2ByTitleLikeOrderByIdDesc("title%");
        Post post = posts.get(0);
        assertEquals(3, post.getId());
    }
    @Test
    @DisplayName("findTop2ByOrderByIdDesc")
        // SELECT * FROM post WHERE ORDER BY id DESC LIMIT 2;
    void t10() {
        List<Post> posts = postService.findTop2ByOrderByIdDesc();
        assertEquals(2, posts.size());

        Post post = posts.get(0);
        assertEquals(3, post.getId());
        assertEquals("title3", post.getTitle());
    }
    @Test
    @DisplayName("findAll(Pageable pageable")
    void t11() {
        int itemsPerPage = 2; // 한 페이지에 보여줄 아이템 수
        int pageNumber = 2; // 현재 페이지 == 2
        pageNumber--;   // 1을 빼는 이유는 jpa는 페이지 번호를 0부터 시작하기 때문
        Pageable pageable = PageRequest.of(pageNumber, itemsPerPage, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> postpage = postService.findAll(pageable);
        List<Post> posts = postpage.getContent();
        assertEquals(1, posts.size());
    }

    @Test
    @DisplayName("findByCommentsAuthorNickname")
    void t15() {
        List<Post> posts = postService.findByCommentsAuthorNickname("유저3");
        assertEquals(1, posts.size());
    }

}