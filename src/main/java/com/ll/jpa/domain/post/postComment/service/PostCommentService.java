package com.ll.jpa.domain.post.postComment.service;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.domain.post.postComment.repository.PostCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    public PostComment write(long postId, String content) {
        PostComment postComment = PostComment.builder()
                .postId(postId)
                .content(content)
                .build();

        return postCommentRepository.save(postComment);
    }
}
