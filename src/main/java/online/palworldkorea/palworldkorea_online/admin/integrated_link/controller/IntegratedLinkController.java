package online.palworldkorea.palworldkorea_online.admin.integrated_link.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.dto.IntegratedLinkDto;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.service.IntegratedLinkService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/integrated-links")
@RequiredArgsConstructor
public class IntegratedLinkController {
    private final IntegratedLinkService integratedLinkService;

    @GetMapping
    public CommonResponse<?> getIntegratedLinks(){
        return CommonResponse.success(SuccessCode.GET_INTEGRATED_LINK_LIST_SUCCESS, integratedLinkService.getIntegratedLinks());
    }

    @PatchMapping
    public CommonResponse<?> updateIntegratedLink(@RequestBody IntegratedLinkDto.Request integratedLinkRequestDto){
        return CommonResponse.success(SuccessCode.UPDATE_INTEGRATED_LINK_SUCCESS, integratedLinkService.updateIntegratedLink(integratedLinkRequestDto));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> deleteIntegratedLink(@PathVariable(name = "id") Long id) {
        return CommonResponse.success(SuccessCode.DELETE_INTEGRATED_LINK_SUCCESS, integratedLinkService.deleteIntegratedLink(id));
    }
}
