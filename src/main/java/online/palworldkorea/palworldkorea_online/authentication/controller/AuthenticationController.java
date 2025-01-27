package online.palworldkorea.palworldkorea_online.authentication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.authentication.dto.TokenDto;
import online.palworldkorea.palworldkorea_online.email.dto.EmailVerificationDto;
import online.palworldkorea.palworldkorea_online.email.service.EmailService;
import online.palworldkorea.palworldkorea_online.authentication.service.AuthenticationService;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @PostMapping("/login")
    public CommonResponse<?> login(@RequestBody @Valid MemberDto.LoginRequest memberRegisterReguestDto) {
        return CommonResponse.success(SuccessCode.LOGIN_SUCCESS, authenticationService.login(memberRegisterReguestDto));
    }

    @PostMapping("/refresh-token")
    public CommonResponse<?> refreshAccessToken(@RequestBody TokenDto tokenDto) {
        System.out.println(tokenDto.getRefreshToken());
        return CommonResponse.success(SuccessCode.REFRESH_ACCESS_TOKEN_SUCCESS, authenticationService.refreshAccessToken(tokenDto));
    }

    @PostMapping("/send-verification-code")
    public CommonResponse<?> sendVerificationCode(@RequestBody EmailVerificationDto.Request emailVerificationRequestDto) {
        emailService.sendVerificationCode(emailVerificationRequestDto);
        return CommonResponse.success(SuccessCode.GENERATE_VERIFICATION_CODE_SUCCESS);
    }

    @PostMapping("/verify-code")
    public CommonResponse<?> verifyCode(@RequestBody EmailVerificationDto.Request emailVerificationRequestDto) {
        if (emailService.verifyCode(emailVerificationRequestDto))
            return CommonResponse.success(SuccessCode.VERIFY_VERTIFICATION_CODE_SUCCESS);
        else
            return CommonResponse.error(ErrorCode.VERIFY_VERTIFICATION_CODE_FAILED);
    }
}
