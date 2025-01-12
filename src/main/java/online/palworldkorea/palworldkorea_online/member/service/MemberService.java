package online.palworldkorea.palworldkorea_online.member.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.AlreadyExistsEmailException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.AlreadyExistsNicknameException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.EmailNotFoundException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidMemberIdException;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDto.Response signUp(MemberDto.RegisterReguest memberRegisterReguestDto) {
        checkIsEmailAlreadySignedUp(memberRegisterReguestDto.getEmail());

        validateNickname(memberRegisterReguestDto.getNickname());

        Member member = memberRegisterReguestDto.toEntity(passwordEncoder);

        memberRepository.save(member);

        return MemberDto.Response.fromEntity(member);
    }

    public MemberDto.Response deleteMember(long id) {
        Member member = getMemberById(id);

        memberRepository.delete(member);

        return MemberDto.Response.fromEntity(member);
    }

    private Member getMemberById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(InvalidMemberIdException::new);
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

    private void validateNickname(String nickname) {
        if (memberRepository.findByNickname(nickname)
                .isPresent()) {
            throw new AlreadyExistsNicknameException();
        }
    }
}
