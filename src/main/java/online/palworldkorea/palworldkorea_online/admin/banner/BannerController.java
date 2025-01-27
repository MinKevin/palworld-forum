package online.palworldkorea.palworldkorea_online.admin.banner;

import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.controller.CommonAdminController;
import online.palworldkorea.palworldkorea_online.admin.common.service.CommonAdminService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/banner")
public class BannerController extends CommonAdminController {

    public BannerController(CommonAdminService commonAdminService) {
        super(commonAdminService);
    }

    @Override
    @GetMapping
    public CommonResponse<?> getCommonAdminInventory() {
        return getCommonAdminInventory(AdminInventoryType.BANNER);
    }
}
