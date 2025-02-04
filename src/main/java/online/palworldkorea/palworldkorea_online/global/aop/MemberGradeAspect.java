package online.palworldkorea.palworldkorea_online.global.aop;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MemberGradeAspect {
    private final MemberService memberService;

    @Pointcut("execution(* online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService.createPost(..)) " +
            "|| execution(* online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService.deletePost(..)) " +
            "|| execution(* online.palworldkorea.palworldkorea_online.post.comment.service.CommentService.updateComment(..)) " +
            "|| execution(* online.palworldkorea.palworldkorea_online.post.comment.service.CommentService.deleteComment(..))")
    public void postOrCommentChange() {}

    @AfterReturning("postOrCommentChange()")
    public void afterPostOrCommentChange() {
        Member member = memberService.getMemberByEmail(MemberUtil.getEmail());
        memberService.updateMemberGrade(member);
    }
}
