package online.palworldkorea.palworldkorea_online.post.free.service;

import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import online.palworldkorea.palworldkorea_online.post.free.dto.FreeDto;
import online.palworldkorea.palworldkorea_online.post.free.entity.Free;
import online.palworldkorea.palworldkorea_online.post.free.mapper.FreeMapper;
import online.palworldkorea.palworldkorea_online.post.free.repository.FreeRepository;
import org.springframework.stereotype.Service;

@Service
public class FreeService extends CommonPostService<
        Free,
        FreeDto.Request,
        FreeDto.Response,
        FreeMapper> {
    public FreeService(MemberService memberService,
                       AttachmentService attachmentService,
                       FreeRepository freeRepository,
                       FreeMapper freeMapper) {
        super(memberService, attachmentService, freeRepository, freeMapper, Free.class);
    }
}
