package online.palworldkorea.palworldkorea_online.post.unified.service;

import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.common.repository.CommonPostRepository;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import online.palworldkorea.palworldkorea_online.post.unified.mapper.UnifiedPostMapper;
import org.springframework.stereotype.Service;

@Service
public class UnifiedPostService extends CommonPostService<
        CommonPost,
        CommonPostDto.Request,
        CommonPostDto.Response,
        UnifiedPostMapper> {
    public UnifiedPostService(MemberService memberService,
                              AttachmentService attachmentService,
                              CommonPostRepository<CommonPost> postRepository,
                              UnifiedPostMapper postMapper) {
        super(memberService, attachmentService, postRepository, postMapper, CommonPost.class);
    }
}
