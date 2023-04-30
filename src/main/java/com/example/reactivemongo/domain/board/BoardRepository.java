package com.example.reactivemongo.domain.board;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface BoardRepository extends ReactiveMongoRepository<Board, String> {
    Flux<Board> findAll();
    Flux<Board> findAllByWriterId(String writerId);
}
