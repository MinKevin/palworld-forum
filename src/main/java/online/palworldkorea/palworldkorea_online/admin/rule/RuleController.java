package online.palworldkorea.palworldkorea_online.admin.rule;

import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.controller.CommonAdminController;
import online.palworldkorea.palworldkorea_online.admin.common.dto.CommonAdminDto;
import online.palworldkorea.palworldkorea_online.admin.common.service.CommonAdminService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/rule")
public class RuleController extends CommonAdminController {
    public RuleController(CommonAdminService commonAdminService) {
        super(commonAdminService, AdminInventoryType.RULE);
    }

    @PatchMapping
    public CommonResponse<?> updateAdminInventory(@RequestBody CommonAdminDto.Request commonAdminRequestDto) {
        return CommonResponse.success(SuccessCode.UPDATE_ADMIN_INVENTORY_SUCCESS,
                commonAdminService.updateAdminInventoryV2(commonAdminRequestDto, adminInventoryType));
    }
}
