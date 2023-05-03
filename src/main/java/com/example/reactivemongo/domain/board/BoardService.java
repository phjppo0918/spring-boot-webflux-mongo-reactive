package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.auth.AuthService;
import com.example.reactivemongo.domain.board.dto.BoardRequest;
import com.example.reactivemongo.domain.board.dto.BoardResponse;
import com.example.reactivemongo.domain.board.dto.BoardSummary;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BoardService {
    BoardRepository boardRepository;
    BoardMapper boardMapper;
    AuthService authService;

    public Mono<BoardResponse> create(final BoardRequest dto) {
        return authService.getLoginUser()
                .map(user -> boardMapper.toEntity(dto, user))
                .flatMap(boardRepository::insert)
                .map(boardMapper::toResponse);
    }

    public Flux<BoardSummary> findAll() {
        return boardRepository.findAll()
                .map(boardMapper::toSummary);
    }

    public Flux<BoardSummary> findAllByWriterId(final String writerId) {
        return boardRepository.findAllByWriterId(writerId)
                .map(boardMapper::toSummary);
    }

    public Mono<BoardResponse> findById(final String id) {
        return boardRepository.findById(id)
                .map(boardMapper::toResponse);
    }
}
