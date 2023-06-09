package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.auth.AuthService;
import com.example.reactivemongo.domain.board.dto.BoardRequest;
import com.example.reactivemongo.domain.board.dto.BoardResponse;
import com.example.reactivemongo.domain.board.dto.BoardSummary;
import com.example.reactivemongo.domain.member.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("BoardService의")
class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @MockBean
    AuthService authService;

    @Autowired
    BoardRepository boardRepository;

    List<BoardRequest> boards = List.of(
            new BoardRequest("title1", "content1"),
            new BoardRequest("title2", "content2"),
            new BoardRequest("title3", "content3"),
            new BoardRequest("title4", "content4")
    );

    @BeforeEach
    public void beforeSetting() {
        when(authService.getLoginUser()).thenReturn(Mono.just(new Member("mockUser", "passwrord")));
        boardRepository.deleteAll().subscribe();
    }

    @Nested
    @DisplayName("create(BoardRequest) 에서")
    class CallCreate {
        @Test
        @DisplayName("생성을 수행하는가")
        void successCreate() {
            //given
            BoardRequest dto = new BoardRequest("title", "content");
            //when
            Mono<BoardResponse> result = boardService.create(dto);
            //then
            StepVerifier.create(result)
                    .expectNextMatches( //expectNextMatches 체이닝해서 사용 불가
                            board ->
                                    board.getTitle().equals(dto.getTitle()) &&
                                            board.getContent().equals(dto.getContent())
                    )
                    // 아래로 대체해서 사용 가능
//                    .assertNext(board -> {
//                       assertThat(board.getTitle()).isEqualTo(dto.getTitle());
//                        assertThat(board.getContent()).isEqualTo(dto.getContent());
//                    })
                    .verifyComplete()
            ;
        }
    }

    @Nested
    @DisplayName("findAll에서")
    class CallFindAll {
        @Test
        @DisplayName("전체 조회를 수행하는가")
        void successFindAll() throws Exception {
            //given
            boards.forEach(b -> boardService.create(b).subscribe());
            //when
            Flux<BoardSummary> result = boardService.findAll();
            //then
            StepVerifier.create(result)
                    .expectNextCount(boards.size())
                    .verifyComplete();
        }
    }
}