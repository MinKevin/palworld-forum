package online.palworldkorea.palworldkorea_online.post.free.controller;

import online.palworldkorea.palworldkorea_online.post.common.controller.CommonPostController;
import online.palworldkorea.palworldkorea_online.post.free.dto.FreeDto;
import online.palworldkorea.palworldkorea_online.post.free.entity.Free;
import online.palworldkorea.palworldkorea_online.post.free.mapper.FreeMapper;
import online.palworldkorea.palworldkorea_online.post.free.service.FreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frees")
public class FreeController extends CommonPostController<
        Free,
        FreeDto.Request,
        FreeDto.Response,
        FreeMapper> {
    public FreeController(FreeService freeService) {
        super(freeService);
    }
}
