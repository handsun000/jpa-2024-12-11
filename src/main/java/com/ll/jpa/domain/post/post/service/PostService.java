package com.ll.jpa.domain.post.post.service;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(String title, String content) {
        Post post = Post
                .builder()
                .title(title)
                .content(content)
                .modifiedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .blind(false)
                .build();

        postRepository.save(post);
        return post;
    }

    public long count() {
        return postRepository.count();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);

//        postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByTitleAndContent(String title, String content) {
        return postRepository.findByTitleAndContent(title,content);
    }

    public List<Post> findByTitleLike(String title) {
        return postRepository.findByTitleLike(title);
    }

    public List<Post> findByTitleLikeOrderByIdDesc(String title) {
        return postRepository.findByTitleLikeOrderByIdDesc(title);
    }

    public List<Post> findByOrderByIdDesc() {
        return postRepository.findByOrderByIdDesc();
    }

    public List<Post> findTop2ByTitleLikeOrderByIdDesc(String title) {
        return postRepository.findTop2ByTitleLikeOrderByIdDesc(title);
    }

    public List<Post> findTop2ByOrderByIdDesc() {
        return postRepository.findTop2ByOrderByIdDesc();
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
