package online.palworldkorea.palworldkorea_online.admin.coalition.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.coalition.dto.CoalitionDto;
import online.palworldkorea.palworldkorea_online.admin.coalition.service.CoalitionService;
import online.palworldkorea.palworldkorea_online.admin.common.dto.CommonAdminDto;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/coalitions")
@RequiredArgsConstructor
public class CoalitionController {
    private final CoalitionService coalitionService;

    @GetMapping
    public CommonResponse<?> getCoalitions() {
        return CommonResponse.success(SuccessCode.GET_COALITION_LIST_SUCCESS, coalitionService.getCoalitions());
    }

    @PatchMapping
    public CommonResponse<?> updateCoalition(@RequestPart(name = "data") CoalitionDto.Request coalitionRequestDto,
                                             @RequestPart(name = "attachment", required = false) MultipartFile attachment) {
        coalitionRequestDto.setAttachment(attachment);
        return CommonResponse.success(SuccessCode.UPDATE_COALITION_SUCCESS, coalitionService.updateCoalition(coalitionRequestDto));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> deleteCoalition(@PathVariable Long id) {
        return CommonResponse.success(SuccessCode.DELETE_COALITION_SUCCESS, coalitionService.deleteCoalition(id));
    }
}
