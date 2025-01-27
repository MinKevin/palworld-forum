package online.palworldkorea.palworldkorea_online.admin.common.mapper;

import online.palworldkorea.palworldkorea_online.admin.common.dto.CommonAdminDto;
import online.palworldkorea.palworldkorea_online.admin.common.entity.CommonAdmin;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommonAdminMapper {
    CommonAdminDto.Response toResponse(CommonAdmin commonAdmin);

    default String map(Member member) {
        return member != null ? member.getEmail() : null;
    }
}
