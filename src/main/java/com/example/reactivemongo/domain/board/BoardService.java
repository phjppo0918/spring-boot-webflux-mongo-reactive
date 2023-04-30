package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.auth.AuthService;
import com.example.reactivemongo.domain.board.dto.BoardRequest;
import com.example.reactivemongo.domain.board.dto.BoardResponse;
import com.example.reactivemongo.domain.board.dto.BoardSummary;
import com.example.reactivemongo.domain.member.MemberService;
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
    MemberService memberService;

    public Mono<BoardResponse> create(final BoardRequest dto) {
        return authService.getLoginUserId()
                .map(user -> boardMapper.toEntity(dto, user))
                .flatMap(boardRepository::insert)
                .zipWith(authService.getLoginUser())
                .map(t -> boardMapper.toResponse(t.getT1(), t.getT2()));
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
                .zipWith(boardRepository
                        .findById(id)
                        .map(Board::getWriterId)
                        .flatMap(memberService::getEntity))
                .map(t -> boardMapper.toResponse(t.getT1(), t.getT2()));
    }
}
