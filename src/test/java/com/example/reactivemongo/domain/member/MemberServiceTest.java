package com.example.reactivemongo.domain.member;

import com.example.reactivemongo.domain.member.dto.MemberRequest;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@DisplayName("MemberService의")
class MemberServiceTest {
    @Autowired MemberService memberService;

    @Nested
    @DisplayName("create(MemberRequest) 에서")
    class callCreate {
        @Test
        @DisplayName("생성을 수행하는가")
        void successCreate() {
            //given
            MemberRequest dto = new MemberRequest("name", "password");
            //when
            Mono<MemberResponse> result = memberService.create(dto);
            //then
            StepVerifier.create(result)
                    .expectNextMatches(m -> m.getName().equals(dto.getName()))
                    .verifyComplete();
        }
    }
}