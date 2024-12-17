package com.ll.jpa.domain.post.post.entity;

import com.ll.jpa.domain.member.member.entity.Member;
import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.domain.post.tag.entity.PostTag;
import com.ll.jpa.global.jpa.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)// default LAZY
    private List<PostTag> tags = new ArrayList<>();

    public void addComment(Member author, String content) {

        PostComment postComment = PostComment.builder()
                .content(content)
                .post(this)
                .author(author)
                .build();

        comments.add(postComment);
    }

    public void addTag(String content) {

        Optional<PostTag> opOldTag = tags
                .stream()
                .filter(tag -> tag.getContent().equals(content))
                .findFirst();

        if (opOldTag.isPresent()) return;

        PostTag postTag = PostTag
                .builder()
                .post(this)
                .content(content)
                .build();

        tags.add(postTag);
    }

    public boolean removeComment(PostComment postComment) {
        return comments.remove(postComment);
    }
}
