package online.palworldkorea.palworldkorea_online.post.data.mapper;

import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import online.palworldkorea.palworldkorea_online.post.data.dto.DataDto;
import online.palworldkorea.palworldkorea_online.post.data.entity.Data;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config= CommonPostMapper.class)
public interface DataMapper extends CommonPostMapper<Data, DataDto.Request, DataDto.Response> {
}
