package online.palworldkorea.palworldkorea_online.admin.member_management.dto;

import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;

public class AdminMemberDto {
    private AdminMemberDto() {}

    @Getter
    @Setter
    public static class Request {
        private MemberRole memberRole;
    }
}
