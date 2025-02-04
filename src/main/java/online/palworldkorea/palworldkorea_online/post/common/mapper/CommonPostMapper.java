package online.palworldkorea.palworldkorea_online.post.common.mapper;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.mapper.AttachmentMapper;
import online.palworldkorea.palworldkorea_online.post.comment.mapper.CommentMapper;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@MapperConfig(uses = {CommentMapper.class, AttachmentMapper.class})
public interface CommonPostMapper<
        E extends CommonPost,
        Q extends CommonPostDto.Request,
        P extends CommonPostDto.Response> extends CommonMapper {
    @Mapping(source = "author", target="author", qualifiedByName = "mapAuthorEmail")
    @Mapping(source = "author", target="nickname", qualifiedByName = "mapAuthorNickname")
    @Mapping(source = "author", target="memberRole", qualifiedByName = "mapAuthorMemberRole")
    @Mapping(source = "comments", target="comments")
    @Mapping(source = "attachments", target="attachments")
    P toResponse(E post);

    @Mapping(target = "comments", ignore = true)
    @Mapping(source = "author", target="author", qualifiedByName = "mapAuthorEmail")
    @Mapping(source = "author", target="nickname", qualifiedByName = "mapAuthorNickname")
    @Mapping(source = "author", target="memberRole", qualifiedByName = "mapAuthorMemberRole")
    P toResponseWithoutComments(E post);
}
