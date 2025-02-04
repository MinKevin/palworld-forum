package online.palworldkorea.palworldkorea_online.admin.common.mapper;

import online.palworldkorea.palworldkorea_online.admin.common.dto.CommonAdminDto;
import online.palworldkorea.palworldkorea_online.admin.common.entity.CommonAdmin;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.mapper.AttachmentMapper;
import online.palworldkorea.palworldkorea_online.post.comment.mapper.CommentMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AttachmentMapper.class})
public interface CommonAdminMapper {
    CommonAdminDto.Response toResponse(CommonAdmin commonAdmin);
}
