package online.palworldkorea.palworldkorea_online.authentication.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.authentication.dto.TokenDto;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidPasswordException;
import online.palworldkorea.palworldkorea_online.global.jwt.JwtTokenUtil;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.mapper.MemberMapper;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public MemberDto.Response login(MemberDto.LoginRequest memberLoginRequestDto) {
        Member member = memberService.getMemberByEmail(memberLoginRequestDto.getEmail());

        checkPassword(memberLoginRequestDto, member);

        TokenDto tokenDto = generateNewTokens(member);

        return memberMapper.toResponse(member, tokenDto);
    }

    public TokenDto refreshAccessToken(String refreshToken) {
        jwtTokenUtil.validateToken(refreshToken);

        String email = jwtTokenUtil.getEmailFromToken(refreshToken);
        List<GrantedAuthority> authorities = jwtTokenUtil.getAuthoritiesFromToken(refreshToken);

        return new TokenDto(null, jwtTokenUtil.generateAccessToken(email, authorities));
    }

    private void checkPassword(MemberDto.LoginRequest memberLoginRequestDto, Member member) {
        if (!member.checkInputPasswordIsCorrect(memberLoginRequestDto.getPassword(), passwordEncoder))
            throw new InvalidPasswordException();
    }

    private TokenDto generateNewTokens(Member member) {
        return new TokenDto(
                jwtTokenUtil.generateRefreshToken(member.getEmail(), member.getAuthorities()),
                jwtTokenUtil.generateAccessToken(member.getEmail(), member.getAuthorities())
        );
    }
}
