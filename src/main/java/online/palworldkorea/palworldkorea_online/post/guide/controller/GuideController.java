package online.palworldkorea.palworldkorea_online.post.guide.controller;

import online.palworldkorea.palworldkorea_online.post.common.controller.CommonPostController;
import online.palworldkorea.palworldkorea_online.post.guide.dto.GuideDto;
import online.palworldkorea.palworldkorea_online.post.guide.entity.Guide;
import online.palworldkorea.palworldkorea_online.post.guide.mapper.GuideMapper;
import online.palworldkorea.palworldkorea_online.post.guide.service.GuideService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guides")
public class GuideController extends CommonPostController<
        Guide,
        GuideDto.Request,
        GuideDto.Response,
        GuideMapper> {
    public GuideController(GuideService guideService) {
        super(guideService);
    }
}
