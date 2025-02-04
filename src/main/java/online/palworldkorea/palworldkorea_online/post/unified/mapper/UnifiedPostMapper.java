package online.palworldkorea.palworldkorea_online.post.unified.mapper;

import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config= CommonPostMapper.class)
public interface UnifiedPostMapper extends CommonPostMapper<CommonPost, CommonPostDto.Request, CommonPostDto.Response> {
}
