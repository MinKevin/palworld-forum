package online.palworldkorea.palworldkorea_online.admin.rule;

import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.controller.CommonAdminController;
import online.palworldkorea.palworldkorea_online.admin.common.service.CommonAdminService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rule")
public class RuleController extends CommonAdminController {
    public RuleController(CommonAdminService commonAdminService) {
        super(commonAdminService);
    }

    @Override
    @GetMapping
    public CommonResponse<?> getCommonAdminInventory() {
        return getCommonAdminInventory(AdminInventoryType.RULE);
    }
}
