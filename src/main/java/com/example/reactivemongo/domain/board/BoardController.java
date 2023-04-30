package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.board.dto.BoardRequest;
import com.example.reactivemongo.domain.board.dto.BoardResponse;
import com.example.reactivemongo.domain.board.dto.BoardSummary;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("boards")
public class BoardController {
    BoardService boardService;

    @PostMapping
    public Mono<BoardResponse> create(@RequestBody BoardRequest dto) {
        return boardService.create(dto);
    }

    @GetMapping
    public Flux<BoardSummary> getAll() {
        return boardService.findAll();
    }

    @GetMapping("{id}")
    public Mono<BoardResponse> getById(@PathVariable String id) {
        return boardService.findById(id);
    }

    @GetMapping(params = "writer-id")
    public Flux<BoardSummary> getAllByWriterId(@RequestParam("writer-id") String writerId, Pageable pageable) {
        return boardService.findAllByWriterId(writerId);
    }
}
