package online.palworldkorea.palworldkorea_online.admin.Footer;

import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.controller.CommonAdminController;
import online.palworldkorea.palworldkorea_online.admin.common.dto.CommonAdminDto;
import online.palworldkorea.palworldkorea_online.admin.common.service.CommonAdminService;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/footer")
public class FooterController extends CommonAdminController {
    public FooterController(CommonAdminService commonAdminService) {
        super(commonAdminService, AdminInventoryType.FOOTER);
    }

    @PatchMapping
    public CommonResponse<?> updateAdminInventory(@RequestPart(name = "data") CommonAdminDto.Request commonAdminRequestDto,
                                                  @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        commonAdminRequestDto.setAttachments(attachments);
        return CommonResponse.success(SuccessCode.UPDATE_ADMIN_INVENTORY_SUCCESS,
                commonAdminService.updateAdminInventoryV1(commonAdminRequestDto, adminInventoryType));
    }
}
