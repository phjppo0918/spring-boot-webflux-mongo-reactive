package com.example.reactivemongo.domain.board.dto;

import com.example.reactivemongo.domain.member.dto.MemberResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoardResponse {
    String id;
    String title;
    String content;
    MemberResponse writer;
}
