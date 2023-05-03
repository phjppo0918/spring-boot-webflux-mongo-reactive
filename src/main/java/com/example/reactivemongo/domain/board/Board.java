package com.example.reactivemongo.domain.board;

import com.example.reactivemongo.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Board {
    @Id
    @NonFinal
    String id;
    String title;
    String content;
    Member writer;
}
