package online.palworldkorea.palworldkorea_online.member.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.AlreadyExistsEmailException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.AlreadyExistsNicknameException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.EmailNotFoundException;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.mapper.MemberMapper;
import online.palworldkorea.palworldkorea_online.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public MemberDto.Response signUp(MemberDto.RegisterReguest memberRegisterReguestDto) {
        checkIsEmailAlreadySignedUp(memberRegisterReguestDto.getEmail());

        checkIsNicknameAlreadySignedUp(memberRegisterReguestDto.getNickname());

        Member member = memberRegisterReguestDto.toEntity(passwordEncoder);

        memberRepository.save(member);

        return memberMapper.toResponse(member);
    }

    public MemberDto.Response deleteMember() {
        Member member = getMemberByEmail(MemberUtil.getEmail());

        memberRepository.delete(member);

        return memberMapper.toResponse(member);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }

    public void checkIsEmailAlreadySignedUp(String email) {
        if (memberRepository.findByEmail(email)
                .isPresent()) {
            throw new AlreadyExistsEmailException();
        }
    }

    private void checkIsNicknameAlreadySignedUp(String nickname) {
        if (memberRepository.findByNickname(nickname)
                .isPresent()) {
            throw new AlreadyExistsNicknameException();
        }
    }
}
