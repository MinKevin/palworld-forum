package online.palworldkorea.palworldkorea_online.admin.link.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.link.dto.LinkDto;
import online.palworldkorea.palworldkorea_online.admin.link.service.LinkService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/links")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @GetMapping
    public CommonResponse<?> getLinks() {
        return CommonResponse.success(SuccessCode.GET_LINK_LIST_SUCCESS, linkService.getLinks());
    }

    @PatchMapping
    public CommonResponse<?> updateLink(@RequestBody LinkDto.Request linkRequestDto) {
        return CommonResponse.success(SuccessCode.UPDATE_LINK_SUCCESS, linkService.updateLink(linkRequestDto));
    }
}
