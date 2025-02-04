package online.palworldkorea.palworldkorea_online.member.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.*;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.member.mapper.MemberMapper;
import online.palworldkorea.palworldkorea_online.member.repository.MemberRepository;
import online.palworldkorea.palworldkorea_online.post.comment.repository.CommentRepository;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public MemberDto.Response getMember() {
        return memberMapper.toResponse(getMemberByEmail(MemberUtil.getEmail()));
    }

    public MemberDto.Response signUp(MemberDto.RegisterReguest memberRegisterReguestDto) {
        checkIsEmailAlreadySignedUp(memberRegisterReguestDto.getEmail());

        checkIsNicknameAlreadySignedUp(memberRegisterReguestDto.getNickname());

        Member member = memberRegisterReguestDto.toEntity(passwordEncoder);

        memberRepository.save(member);

        return memberMapper.toResponse(member);
    }

    public MemberDto.Response changeInfo(MemberDto.ChangeInfoReguest memberChangeInfoReguestDto) {
        Member member = getMemberByEmail(MemberUtil.getEmail());

        changePassword(memberChangeInfoReguestDto, member);

        changeNickname(memberChangeInfoReguestDto, member);

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

    private void changePassword(MemberDto.ChangeInfoReguest memberChangeInfoReguestDto, Member member) {
        if (memberChangeInfoReguestDto.getPassword() != null) {
            BindingResult bindingResult = new BeanPropertyBindingResult(memberChangeInfoReguestDto, "memberChangeInfoReguestDto");
            validator.validate(memberChangeInfoReguestDto.getPassword(), bindingResult);

            if (bindingResult.hasErrors())
                throw new InvalidPasswordException();

            member.updatePassword(memberChangeInfoReguestDto.getEncodedPassword(passwordEncoder));
        }
    }

    private void changeNickname(MemberDto.ChangeInfoReguest memberChangeInfoReguestDto, Member member) {
        if (memberChangeInfoReguestDto.getNickname() != null) {
            BindingResult bindingResult = new BeanPropertyBindingResult(memberChangeInfoReguestDto, "memberChangeInfoReguestDto");
            validator.validate(memberChangeInfoReguestDto.getNickname(), bindingResult);

            if (bindingResult.hasErrors())
                throw new AlreadyExistsNicknameException();

            member.updateNickname(memberChangeInfoReguestDto.getNickname());
        }
    }

    public Member validateIsAdmin() {
        Member admin = getMemberByEmail(MemberUtil.getEmail());
        if (admin.getMemberRole() != MemberRole.ADMIN)
            throw new AccessDeniedException();

        return admin;
    }

    public void updateMemberGrade(Member member) {
        if (member.getMemberRole() == MemberRole.ADMIN || member.getMemberRole() == MemberRole.PARTNER)
            return;

        List<CommonPost> posts = member.getPosts();
        long countOfPosts = posts.size();
        Long countOfComments = commentRepository.countById(member.getId());

        long totalScore = countOfPosts * 3 + countOfComments;
        System.out.println(countOfPosts + " " + countOfComments);
        List<MemberRole> memberRoles = Arrays.stream(MemberRole.values())
                .sorted((role1, role2) -> Integer.compare(role2.getCriteria(), role1.getCriteria()))
                .toList();

        for (MemberRole memberRole : memberRoles) {
            if (memberRole != MemberRole.ADMIN && memberRole != MemberRole.PARTNER) {
                if (totalScore >= memberRole.getCriteria()) {
                    member.updateMemberRole(memberRole);
                    break;
                }
            }
        }
    }
}
