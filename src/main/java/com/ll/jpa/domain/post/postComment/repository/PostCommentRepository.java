package com.ll.jpa.domain.post.postComment.repository;

import com.ll.jpa.domain.post.postComment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
