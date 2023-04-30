package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.board.dto.BoardRequest;
import com.example.reactivemongo.domain.board.dto.BoardResponse;
import com.example.reactivemongo.domain.board.dto.BoardSummary;
import com.example.reactivemongo.domain.member.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BoardMapper {
    Board toEntity(BoardRequest dto, String writerId);
    @Mapping(target = "id", source = "entity.id")
    BoardResponse toResponse(Board entity, Member writer);
    BoardSummary toSummary(Board entity);
}
