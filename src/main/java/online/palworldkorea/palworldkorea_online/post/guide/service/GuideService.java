package online.palworldkorea.palworldkorea_online.post.guide.service;

import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import online.palworldkorea.palworldkorea_online.post.guide.dto.GuideDto;
import online.palworldkorea.palworldkorea_online.post.guide.entity.Guide;
import online.palworldkorea.palworldkorea_online.post.guide.mapper.GuideMapper;
import online.palworldkorea.palworldkorea_online.post.guide.repository.GuideRepository;
import org.springframework.stereotype.Service;

@Service
public class GuideService extends CommonPostService<
        Guide,
        GuideDto.Request,
        GuideDto.Response,
        GuideMapper> {
    public GuideService(MemberService memberService,
                        AttachmentService attachmentService,
                        GuideRepository guideRepository,
                        GuideMapper guideMapper) {
        super(memberService, attachmentService, guideRepository, guideMapper, Guide.class);
    }
}
