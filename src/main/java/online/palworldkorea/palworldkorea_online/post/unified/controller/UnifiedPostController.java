package online.palworldkorea.palworldkorea_online.post.unified.controller;

import online.palworldkorea.palworldkorea_online.post.common.controller.CommonPostController;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.unified.mapper.UnifiedPostMapper;
import online.palworldkorea.palworldkorea_online.post.unified.service.UnifiedPostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class UnifiedPostController extends CommonPostController<
        CommonPost,
        CommonPostDto.Request,
        CommonPostDto.Response,
        UnifiedPostMapper> {
    public UnifiedPostController(UnifiedPostService unifiedPostService) {
        super(unifiedPostService);
    }
}
