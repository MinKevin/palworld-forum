package online.palworldkorea.palworldkorea_online.admin.integrated_link.mapper;

import online.palworldkorea.palworldkorea_online.admin.integrated_link.dto.IntegratedLinkDto;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.entity.IntegratedLink;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IntegratedLinkMapper {
    IntegratedLinkDto.Response toResponse(IntegratedLink integratedLink);
}
