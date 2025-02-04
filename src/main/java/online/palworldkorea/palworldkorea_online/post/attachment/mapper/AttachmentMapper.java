package online.palworldkorea.palworldkorea_online.post.attachment.mapper;

import online.palworldkorea.palworldkorea_online.post.attachment.dto.AttachmentDto;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    @Mapping(source = "author.email", target="author")
    AttachmentDto.Response toResponse(Attachment attachment);
}
