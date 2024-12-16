package com.ll.jpa.domain.member.member.entity;

import com.ll.jpa.global.jpa.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseTime {

    @Column(unique = true, length = 30)
    private String username;

    @Column(length = 100)
    private String password;

    private String nickname;
}
