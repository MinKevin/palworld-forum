package online.palworldkorea.palworldkorea_online.post.promotion.controller;

import online.palworldkorea.palworldkorea_online.post.common.controller.CommonPostController;
import online.palworldkorea.palworldkorea_online.post.promotion.dto.PromotionDto;
import online.palworldkorea.palworldkorea_online.post.promotion.entity.Promotion;
import online.palworldkorea.palworldkorea_online.post.promotion.mapper.PromotionMapper;
import online.palworldkorea.palworldkorea_online.post.promotion.service.PromotionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotions")
public class PromotionController extends CommonPostController<
        Promotion,
        PromotionDto.Request,
        PromotionDto.Response,
        PromotionMapper> {
    public PromotionController(PromotionService promotionService) {
        super(promotionService);
    }
}