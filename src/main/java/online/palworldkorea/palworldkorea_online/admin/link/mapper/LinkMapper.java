package online.palworldkorea.palworldkorea_online.admin.link.mapper;

import online.palworldkorea.palworldkorea_online.admin.link.dto.LinkDto;
import online.palworldkorea.palworldkorea_online.admin.link.entity.Link;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LinkMapper {
    LinkDto.Response toResponse(Link link);
}
