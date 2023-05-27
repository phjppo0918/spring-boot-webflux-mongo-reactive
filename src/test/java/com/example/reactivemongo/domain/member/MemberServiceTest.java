package com.example.reactivemongo.domain.member;

import com.example.reactivemongo.domain.member.dto.MemberRequest;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@Slf4j
@SpringBootTest
@DisplayName("MemberService의")
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;
    List<MemberRequest> members = List.of(
            new MemberRequest("member1", "password1"),
            new MemberRequest("member2", "password2"),
            new MemberRequest("member3", "password3"));

    @BeforeEach
    void beforeEach() {
        memberRepository.deleteAll().subscribe();
    }

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

    @Nested
    @DisplayName("update(id, dto) 에서")
    class CallUpdate {
        @Test
        @DisplayName("수정을 수행하는가")
        void successUpdate() throws Exception {
            //given
            MemberRequest dto = new MemberRequest("name", "password");
            MemberRequest updateDto = new MemberRequest("newName", "newPassword");
            MemberResponse target = memberService.create(dto).block();

            //when
            Mono<MemberResponse> result = memberService.update(target.getId(), updateDto);
            //then
            StepVerifier.create(result)
                    .expectNextMatches(member ->
                        member.getId().equals(target.getId()) &&
                        member.getName().equals(updateDto.getName()))
                    .verifyComplete();

        }
    }
}