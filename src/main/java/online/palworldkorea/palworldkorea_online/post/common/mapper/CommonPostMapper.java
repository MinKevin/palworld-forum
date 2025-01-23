package online.palworldkorea.palworldkorea_online.post.common.mapper;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface CommonPostMapper<
        E extends CommonPost,
        Q extends CommonPostDto.Request,
        P extends CommonPostDto.Response> {
    P toResponse(E post);

    @Mapping(target = "comments", ignore = true)
    P toResponseWithoutComments(E post);

    default String map(Member member) {
        return member != null ? member.getEmail() : null;
    }
}
