package com.ll.jpa.domain.post.post.entity;

import com.ll.jpa.domain.member.member.entity.Member;
import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.global.jpa.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post extends BaseTime {

    @Column(length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean blind;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true) // default LAZY
    private List<PostComment> comments = new ArrayList<>();

    public void addComment(Member author, String content) {
        PostComment postComment = PostComment.builder()
                .content(content)
                .post(this)
                .author(author)
                .build();

        comments.add(postComment);
    }

    public void removeComment(PostComment postComment) {
        comments.remove(postComment);
    }
}
