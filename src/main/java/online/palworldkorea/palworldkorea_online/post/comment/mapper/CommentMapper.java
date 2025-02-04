package online.palworldkorea.palworldkorea_online.post.comment.mapper;

import online.palworldkorea.palworldkorea_online.post.comment.dto.CommentDto;
import online.palworldkorea.palworldkorea_online.post.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target="author", expression = "java(comment.getAuthor() != null ? comment.getAuthor().getEmail() : null)")
    @Mapping(target="nickname", expression = "java(comment.getAuthor() != null ? comment.getAuthor().getNickname() : null)")
    @Mapping(target="memberRole", expression = "java(comment.getAuthor() != null ? comment.getAuthor().getMemberRole() : null)")
    CommentDto.Response toResponse(Comment comment);
}