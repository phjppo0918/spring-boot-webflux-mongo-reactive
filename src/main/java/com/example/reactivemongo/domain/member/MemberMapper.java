package com.example.reactivemongo.domain.member;

import com.example.reactivemongo.domain.member.dto.MemberRequest;
import com.example.reactivemongo.domain.member.dto.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberMapper {
    Member toEntity(MemberRequest dto);
    MemberResponse toResponse(Member entity);
}
