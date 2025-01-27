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
        memberService.validateIsAdmin();

        CommonAdmin commonAdmin = getCommonAdminEntity(adminInventoryType);

        return commonAdminMapper.toResponse(commonAdmin);
    }

    public CommonAdminDto.Response updateAdminInventory(CommonAdminDto.Request commonAdminRequestDto) {
        memberService.validateIsAdmin();

        CommonAdmin commonAdmin = getCommonAdminEntity(commonAdminRequestDto.getAdminInventoryType());
        Member author = memberService.getMemberByEmail(MemberUtil.getEmail());

        attachmentService.deleteAttachments(commonAdmin.getAttachments());

        List<Attachment> attachments = attachmentService.getAttachments(commonAdminRequestDto.getAttachments(), author);

        commonAdmin.update(commonAdminRequestDto.getContent(), attachments);
        commonAdminRepository.save(commonAdmin);

        return commonAdminMapper.toResponse(commonAdmin);
    }

    private CommonAdmin getCommonAdminEntity(AdminInventoryType adminInventoryType) {
        return commonAdminRepository.findByAdminInventoryType(adminInventoryType)
                .orElse(new CommonAdmin(adminInventoryType));
    }
}
