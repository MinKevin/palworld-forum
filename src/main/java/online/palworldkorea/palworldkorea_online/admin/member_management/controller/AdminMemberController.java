package online.palworldkorea.palworldkorea_online.admin.member_management.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.member_management.service.AdminMemberService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    @GetMapping
    public CommonResponse<?> getMembers() {
        return CommonResponse.success(SuccessCode.GET_MEMBERS_SUCCESS, adminMemberService.getMembers());
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> deleteMember(@PathVariable(name = "id") long id) {
        return CommonResponse.success(SuccessCode.DELETE_MEMBER_SUCCESS, adminMemberService.deleteMember(id));
    }

    @PatchMapping("/{id}")
    public CommonResponse<?> updateMemberRole(@PathVariable(name = "id") long id, @RequestBody MemberRole memberRole) {
        return CommonResponse.success(SuccessCode.UPDATE_MEMBER_ROLE_SUCCESS, adminMemberService.updateMemberRole(id, memberRole));
    }
}
