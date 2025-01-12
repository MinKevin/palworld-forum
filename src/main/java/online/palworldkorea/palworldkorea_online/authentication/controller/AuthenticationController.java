package online.palworldkorea.palworldkorea_online.authentication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.authentication.service.AuthenticationService;
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

    @PostMapping("/login")
    public CommonResponse<?> login(@RequestBody @Valid MemberDto.LoginRequest memberRegisterReguestDto) {
        return CommonResponse.success(SuccessCode.LOGIN_SUCCESS, authenticationService.login(memberRegisterReguestDto));
    }

    @PostMapping("/refresh-token")
    public CommonResponse<?> refreshAccessToken(@RequestBody String refreshToken) {
        return CommonResponse.success(SuccessCode.REFRESH_ACCESS_TOKEN_SUCCESS, authenticationService.refreshAccessToken(refreshToken));
    }
}
