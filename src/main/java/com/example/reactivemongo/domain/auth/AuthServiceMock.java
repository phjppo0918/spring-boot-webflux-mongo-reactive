package com.example.reactivemongo.domain.auth;

import com.example.reactivemongo.domain.member.Member;
import com.example.reactivemongo.domain.member.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthServiceMock implements AuthService {
    MemberRepository memberRepository;
    @Override
    public Mono<String> getLoginUserId() {
        return memberRepository.findAll().map(Member::getId).last();
    }

    @Override
    public Mono<Member> getLoginUser() {
        return memberRepository.findAll().last();
    }
}
