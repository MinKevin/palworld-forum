package online.palworldkorea.palworldkorea_online.member.mapper;

import online.palworldkorea.palworldkorea_online.authentication.dto.TokenDto;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberDto.Response toResponse(Member member);
    MemberDto.Response toResponse(Member member, TokenDto token);
}
