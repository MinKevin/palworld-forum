package online.palworldkorea.palworldkorea_online.post.promotion.service;

import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import online.palworldkorea.palworldkorea_online.post.promotion.dto.PromotionDto;
import online.palworldkorea.palworldkorea_online.post.promotion.entity.Promotion;
import online.palworldkorea.palworldkorea_online.post.promotion.mapper.PromotionMapper;
import online.palworldkorea.palworldkorea_online.post.promotion.repository.PromotionRepository;
import org.springframework.stereotype.Service;

@Service
public class PromotionService extends CommonPostService<
        Promotion,
        PromotionDto.Request,
        PromotionDto.Response,
        PromotionMapper> {
    public PromotionService(MemberService memberService,
                            AttachmentService attachmentService,
                            PromotionRepository promotionRepository,
                            PromotionMapper promotionMapper) {
        super(memberService, attachmentService, promotionRepository, promotionMapper, Promotion.class);
    }
}
