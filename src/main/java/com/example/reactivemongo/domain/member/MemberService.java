package com.example.reactivemongo.domain.member;

import com.example.reactivemongo.domain.member.dto.MemberRequest;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberService {
    MemberRepository memberRepository;
    MemberMapper memberMapper;

    public Mono<MemberResponse> create(final MemberRequest dto) {
        return memberRepository.insert(memberMapper.toEntity(dto))
                .map(memberMapper::toResponse);
    }

    public Flux<MemberResponse> findAll() {
        return memberRepository.findAll().map(memberMapper::toResponse);
    }

    public Mono<MemberResponse> findById(final String id) {
        return getEntity(id).map(memberMapper::toResponse);
    }

    public Mono<Member> getEntity(final String id) {
        return memberRepository.findById(id);
    }

    public Mono<MemberResponse> update(String id, MemberRequest dto) {
        return getEntity(id).flatMap(m -> {
            m.update(dto.getName(), dto.getPassword());
            return memberRepository.save(m);
        }).map(memberMapper::toResponse);
    }
}
