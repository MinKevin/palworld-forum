package online.palworldkorea.palworldkorea_online.admin.coalition.mapper;

import online.palworldkorea.palworldkorea_online.admin.coalition.dto.CoalitionDto;
import online.palworldkorea.palworldkorea_online.admin.coalition.entity.Coalition;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoalitionMapper {
    CoalitionDto.Response toResponse(Coalition coalition);

    default String map(Member member) {
        return member != null ? member.getEmail() : null;
    }

}
