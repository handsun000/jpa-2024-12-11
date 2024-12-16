package com.ll.jpa.domain.member.member.service;

import com.ll.jpa.domain.member.member.entity.Member;
import com.ll.jpa.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(String userName, String password, String nickName) {
        Member member = Member.builder()
                .username(userName)
                .password(password)
                .nickname(nickName)
                .build();

        return memberRepository.save(member);
    }

    public long count() {
        return memberRepository.count();
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
