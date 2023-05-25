package com.example.reactivemongo.domain.member;

import com.example.reactivemongo.domain.member.dto.MemberRequest;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberController {
    MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MemberResponse> save(@RequestBody MemberRequest dto) {
        return memberService.create(dto);
    }

    @GetMapping
    public Flux<MemberResponse> getAll() {
        return memberService.findAll();
    }

    @GetMapping("{id}")
    public Mono<MemberResponse> getById(@PathVariable String id) {
        return memberService.findById(id);
    }

    @PutMapping("{id}")
    public Mono<MemberResponse> putById(@PathVariable String id, @RequestBody MemberRequest memberRequest) {
        return memberService.update(id, memberRequest);
    }
}
