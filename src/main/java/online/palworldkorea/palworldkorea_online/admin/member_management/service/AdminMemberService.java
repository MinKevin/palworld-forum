package online.palworldkorea.palworldkorea_online.admin.member_management.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidMemberIdException;
import online.palworldkorea.palworldkorea_online.member.dto.MemberDto;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.member.mapper.MemberMapper;
import online.palworldkorea.palworldkorea_online.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminMemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    
    public List<MemberDto.Response> getMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toResponse)
                .toList();
    }

    public MemberDto.Response deleteMember(long id) {
        Member member = getMemberById(id);

        memberRepository.delete(member);

        return memberMapper.toResponse(member);
    }

    private Member getMemberById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(InvalidMemberIdException::new);
    }

    public MemberDto.Response updateMemberRole(long id, MemberRole memberRole) {
        Member member = getMemberById(id);

        member.changeMemberRole(memberRole);

        return memberMapper.toResponse(member);
    }
}
