package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.board.dto.BoardRequest;
import com.example.reactivemongo.domain.board.dto.BoardResponse;
import com.example.reactivemongo.domain.board.dto.BoardSummary;
import com.example.reactivemongo.domain.member.Member;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-30T16:55:17+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.5 (Homebrew)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board toEntity(BoardRequest dto, String writerId) {
        if ( dto == null && writerId == null ) {
            return null;
        }

        String title = null;
        String content = null;
        if ( dto != null ) {
            title = dto.getTitle();
            content = dto.getContent();
        }
        String writerId1 = null;
        writerId1 = writerId;

        Board board = new Board( title, content, writerId1 );

        return board;
    }

    @Override
    public BoardResponse toResponse(Board entity, Member writer) {
        if ( entity == null && writer == null ) {
            return null;
        }

        BoardResponse.BoardResponseBuilder boardResponse = BoardResponse.builder();

        if ( entity != null ) {
            boardResponse.id( entity.getId() );
            boardResponse.title( entity.getTitle() );
            boardResponse.content( entity.getContent() );
        }
        boardResponse.writer( memberToMemberResponse( writer ) );

        return boardResponse.build();
    }

    @Override
    public BoardSummary toSummary(Board entity) {
        if ( entity == null ) {
            return null;
        }

        BoardSummary.BoardSummaryBuilder boardSummary = BoardSummary.builder();

        boardSummary.id( entity.getId() );
        boardSummary.title( entity.getTitle() );
        boardSummary.writerId( entity.getWriterId() );

        return boardSummary.build();
    }

    protected MemberResponse memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponse.MemberResponseBuilder memberResponse = MemberResponse.builder();

        memberResponse.id( member.getId() );
        memberResponse.name( member.getName() );

        return memberResponse.build();
    }
}
