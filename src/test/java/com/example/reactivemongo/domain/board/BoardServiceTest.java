package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.auth.AuthService;
import com.example.reactivemongo.domain.board.dto.BoardRequest;
import com.example.reactivemongo.domain.board.dto.BoardResponse;
import com.example.reactivemongo.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("BoardService의")
class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @MockBean
    AuthService authService;

    @Nested
    @DisplayName("create(BoardRequest) 에서")
    class callCreate {
        @Test
        @DisplayName("생성을 수행하는가")
        void successCreate() {
            //given
            BoardRequest dto = new BoardRequest("title", "content");
            when(authService.getLoginUser()).thenReturn(Mono.just(new Member("mockUser", "passwrord")));
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
}