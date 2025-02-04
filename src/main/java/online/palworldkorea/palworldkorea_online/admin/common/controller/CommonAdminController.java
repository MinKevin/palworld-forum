package online.palworldkorea.palworldkorea_online.admin.common.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.service.CommonAdminService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
public abstract class CommonAdminController {
    protected final CommonAdminService commonAdminService;
    protected final AdminInventoryType adminInventoryType;

    @GetMapping
    public CommonResponse<?> getCommonAdminInventory() {
        return CommonResponse.success(SuccessCode.GET_ADMIN_INVENTORY_SUCCESS,
                commonAdminService.getCommonAdminInventory(adminInventoryType));
    }
}
