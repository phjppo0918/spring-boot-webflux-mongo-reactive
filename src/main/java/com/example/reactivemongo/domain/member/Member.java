package com.example.reactivemongo.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.data.annotation.Id;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Member {
    @Id
    @NonFinal
    String id;
    String name;
    String password;
    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }

    void update(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
