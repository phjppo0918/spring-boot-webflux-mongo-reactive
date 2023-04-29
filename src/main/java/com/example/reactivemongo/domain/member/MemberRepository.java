package com.example.reactivemongo.domain.member;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MemberRepository extends ReactiveMongoRepository<Member, String> {
}
