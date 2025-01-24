package online.palworldkorea.palworldkorea_online.post.guide.mapper;

import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import online.palworldkorea.palworldkorea_online.post.guide.dto.GuideDto;
import online.palworldkorea.palworldkorea_online.post.guide.entity.Guide;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuideMapper extends CommonPostMapper<Guide, GuideDto.Request, GuideDto.Response> {
}
