package com.ll.jpa.domain.post.post.repository;

import com.ll.jpa.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}