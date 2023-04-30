package com.example.reactivemongo.domain.auth;

import com.example.reactivemongo.domain.member.Member;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<String> getLoginUserId();
    Mono<Member> getLoginUser();
}
