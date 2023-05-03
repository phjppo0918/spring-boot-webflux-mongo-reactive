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
    date = "2023-05-03T16:51:15+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.5 (Homebrew)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board toEntity(BoardRequest dto, Member writer) {
        if ( dto == null && writer == null ) {
            return null;
        }

        String title = null;
        String content = null;
        if ( dto != null ) {
            title = dto.getTitle();
            content = dto.getContent();
        }
        Member writer1 = null;
        writer1 = writer;

        Board board = new Board( title, content, writer1 );

        return board;
    }

    @Override
    public BoardResponse toResponse(Board entity) {
        if ( entity == null ) {
            return null;
        }

        BoardResponse.BoardResponseBuilder boardResponse = BoardResponse.builder();

        boardResponse.id( entity.getId() );
        boardResponse.title( entity.getTitle() );
        boardResponse.content( entity.getContent() );
        boardResponse.writer( memberToMemberResponse( entity.getWriter() ) );

        return boardResponse.build();
    }

    @Override
    public BoardSummary toSummary(Board entity) {
        if ( entity == null ) {
            return null;
        }

        BoardSummary.BoardSummaryBuilder boardSummary = BoardSummary.builder();

        boardSummary.writerId( entityWriterId( entity ) );
        boardSummary.id( entity.getId() );
        boardSummary.title( entity.getTitle() );

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

    private String entityWriterId(Board board) {
        if ( board == null ) {
            return null;
        }
        Member writer = board.getWriter();
        if ( writer == null ) {
            return null;
        }
        String id = writer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
