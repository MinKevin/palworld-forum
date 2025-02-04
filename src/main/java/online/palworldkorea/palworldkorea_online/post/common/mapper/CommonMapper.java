package online.palworldkorea.palworldkorea_online.post.common.mapper;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import org.mapstruct.Named;

public interface CommonMapper {
    @Named("mapAuthorEmail")
    default String mapAuthorEmail(Member member) {
        return member != null ? member.getEmail() : null;
    }

    @Named("mapAuthorNickname")
    default String mapAuthorNickname(Member member) {
        return member != null ? member.getNickname() : null;
    }

    @Named("mapAuthorMemberRole")
    default MemberRole mapAuthorMemberRole(Member member) {
        return member != null ? member.getMemberRole() : null;
    }
}
