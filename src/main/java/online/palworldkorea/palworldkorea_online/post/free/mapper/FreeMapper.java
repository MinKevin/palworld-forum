package online.palworldkorea.palworldkorea_online.post.free.mapper;

import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import online.palworldkorea.palworldkorea_online.post.free.dto.FreeDto;
import online.palworldkorea.palworldkorea_online.post.free.entity.Free;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config= CommonPostMapper.class)
public interface FreeMapper extends CommonPostMapper<Free, FreeDto.Request, FreeDto.Response> {
}
