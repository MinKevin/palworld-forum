package online.palworldkorea.palworldkorea_online.admin.common.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.dto.CommonAdminDto;
import online.palworldkorea.palworldkorea_online.admin.common.service.CommonAdminService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
public abstract class CommonAdminController {
    private final CommonAdminService commonAdminService;

    public abstract CommonResponse<?> getCommonAdminInventory();

    public CommonResponse<?> getCommonAdminInventory(AdminInventoryType adminInventoryType) {
        return CommonResponse.success(SuccessCode.GET_ADMIN_INVENTORY_SUCCESS,
                commonAdminService.getCommonAdminInventory(adminInventoryType));
    }

    @PatchMapping
    public CommonResponse<?> updateAdminInventory(@RequestPart(name = "data") CommonAdminDto.Request commonAdminRequestDto,
                                                  @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        commonAdminRequestDto.setAttachments(attachments);
        return CommonResponse.success(SuccessCode.UPDATE_ADMIN_INVENTORY_SUCCESS,
                commonAdminService.updateAdminInventory(commonAdminRequestDto));
    }
}
