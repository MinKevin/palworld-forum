package online.palworldkorea.palworldkorea_online.admin.common.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.admin.common.dto.CommonAdminDto;
import online.palworldkorea.palworldkorea_online.admin.common.entity.CommonAdmin;
import online.palworldkorea.palworldkorea_online.admin.common.mapper.CommonAdminMapper;
import online.palworldkorea.palworldkorea_online.admin.common.repository.CommonAdminRepository;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonAdminService {
    private final AttachmentService attachmentService;
    private final MemberService memberService;
    private final CommonAdminRepository commonAdminRepository;
    private final CommonAdminMapper commonAdminMapper;

    public CommonAdminDto.Response getCommonAdminInventory(AdminInventoryType adminInventoryType) {
        CommonAdmin commonAdmin = getCommonAdminEntity(adminInventoryType);

        return commonAdminMapper.toResponse(commonAdmin);
    }

    public CommonAdminDto.Response updateAdminInventoryV1(CommonAdminDto.Request commonAdminRequestDto, AdminInventoryType adminInventoryType) {
        memberService.validateIsAdmin();

        CommonAdmin commonAdmin = getCommonAdminEntity(adminInventoryType);

        attachmentService.deleteAttachments(commonAdmin.getAttachments());

        List<Attachment> attachments = attachmentService.getAttachments(commonAdminRequestDto.getAttachments());

        commonAdmin.update(commonAdminRequestDto.getContent(), attachments);

        commonAdminRepository.save(commonAdmin);

        return commonAdminMapper.toResponse(commonAdmin);
    }

    public CommonAdminDto.Response updateAdminInventoryV2(CommonAdminDto.Request commonAdminRequestDto, AdminInventoryType adminInventoryType) {
        memberService.validateIsAdmin();

        CommonAdmin commonAdmin = getCommonAdminEntity(adminInventoryType);

        commonAdmin.update(commonAdminRequestDto.getContent(), null);

        commonAdminRepository.save(commonAdmin);

        return commonAdminMapper.toResponse(commonAdmin);
    }

    private CommonAdmin getCommonAdminEntity(AdminInventoryType adminInventoryType) {
        return commonAdminRepository.findByAdminInventoryType(adminInventoryType)
                .orElse(new CommonAdmin(adminInventoryType));
    }
}
