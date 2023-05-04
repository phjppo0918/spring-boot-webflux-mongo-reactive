package com.example.reactivemongo.domain.member;

import com.example.reactivemongo.domain.member.dto.MemberRequest;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@SpringBootTest
@DisplayName("MemberService의")
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    List<MemberRequest> members = List.of(
            new MemberRequest("member1", "password1"),
            new MemberRequest("member2", "password2"),
            new MemberRequest("member3", "password3"));

    @Nested
    @DisplayName("create(MemberRequest) 에서")
    class CallCreate {
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

    @Nested
    @DisplayName("findAll() 에서")
    class CallFindAll {
        @Test
        @DisplayName("조회를 수행하는가")
        void successFindAll() throws Exception {
            //given
            members.forEach(m -> memberService.create(m).subscribe());
            //when
            Flux<MemberResponse> result = memberService.findAll();
            //then
            StepVerifier.create(result)
                    .expectNextCount(members.size())
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("findById(String) 에서")
    class CallFindById {
        @Test
        @DisplayName("조회를 수행하는가")
        void successFind() throws Exception {
            //given
            MemberResponse target = members.stream().map(memberService::create).map(Mono::block).findAny().get();
            //when
            Mono<MemberResponse> result = memberService.findById(target.getId());
            //then
            StepVerifier.create(result)
                    .expectNextMatches( //expectNextMatches 체이닝해서 사용 불가
                            member ->
                                    member.getId().equals(target.getId()) &&
                                            member.getName().equals(target.getName())
                    )
                    .verifyComplete();
        }
    }
}