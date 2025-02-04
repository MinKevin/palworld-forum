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

    @GetMapping
    public CommonResponse<?> getMember() {
        return CommonResponse.success(SuccessCode.GET_MEMBER_SUCCESS, memberService.getMember());
    }

    @PostMapping
    public CommonResponse<?> signUp(@RequestBody @Valid MemberDto.RegisterReguest memberRegisterReguestDto) {
        return CommonResponse.success(SuccessCode.REGISTER_MEMBER_SUCCESS, memberService.signUp(memberRegisterReguestDto));
    }

    @PatchMapping
    public CommonResponse<?> changeInfo(@RequestBody MemberDto.ChangeInfoReguest memberChangeInfoReguestDto) {
        return CommonResponse.success(SuccessCode.CHANGE_PASSWORD_SUCCESS, memberService.changeInfo(memberChangeInfoReguestDto));
    }

    @DeleteMapping
    public CommonResponse<?> deleteMember() {
        return CommonResponse.success(SuccessCode.DELETE_MEMBER_SUCCESS, memberService.deleteMember());
    }
}