package com.example.reactivemongo.domain.member;

import javax.annotation.processing.Generated;

import com.example.reactivemongo.domain.member.dto.MemberRequest;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-29T09:45:23+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.5 (Homebrew)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member toEntity(MemberRequest dto) {
        if ( dto == null ) {
            return null;
        }

        String name = null;
        String password = null;

        name = dto.getName();
        password = dto.getPassword();

        Member member = new Member( name, password );

        return member;
    }

    @Override
    public MemberResponse toResponse(Member entity) {
        if ( entity == null ) {
            return null;
        }

        MemberResponse.MemberResponseBuilder memberResponse = MemberResponse.builder();

        memberResponse.id( entity.getId() );
        memberResponse.name( entity.getName() );

        return memberResponse.build();
    }
}
