package com.ll.jpa.domain.post.tag.entity;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.global.jpa.entity.BaseEntity;
import com.ll.jpa.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostTag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(length = 30)
    @EqualsAndHashCode.Include
    private String content;
}