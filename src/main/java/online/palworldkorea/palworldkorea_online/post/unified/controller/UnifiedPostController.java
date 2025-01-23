package online.palworldkorea.palworldkorea_online.post.unified.controller;

import online.palworldkorea.palworldkorea_online.post.common.controller.CommonPostController;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.unified.mapper.UnifiedPostMapper;
import online.palworldkorea.palworldkorea_online.post.unified.service.UnifiedPostService;
import org.springframework.stereotype.Controller;

@Controller
public class UnifiedPostController extends CommonPostController<
        CommonPost,
        CommonPostDto.Request,
        CommonPostDto.Response,
        UnifiedPostMapper> {
    public UnifiedPostController(UnifiedPostService unifiedPostService) {
        super(unifiedPostService);
    }
}
