package com.ll.jpa.domain.member.member.service;

import com.ll.jpa.domain.member.member.entity.Member;
import com.ll.jpa.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(String userName, String password, String nickName) {
        Member member = Member.builder()
                .userName(userName)
                .password(password)
                .nickName(nickName)
                .build();

        return memberRepository.save(member);
    }

    public long count() {
        return memberRepository.count();
    }
}
