package online.palworldkorea.palworldkorea_online.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public CommonResponse<?> signUp(@RequestBody @Valid MemberDto.RegisterReguest memberRegisterReguestDto) {
        return CommonResponse.success(SuccessCode.REGISTER_MEMBER_SUCCESS, memberService.signUp(memberRegisterReguestDto));
    }

    @DeleteMapping
    public CommonResponse<?> deleteMember() {
        return CommonResponse.success(SuccessCode.DELETE_MEMBER_SUCCESS, memberService.deleteMember());
    }
}