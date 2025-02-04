package online.palworldkorea.palworldkorea_online.post.announcement.mapper;

import online.palworldkorea.palworldkorea_online.post.announcement.dto.AnnouncementDto;
import online.palworldkorea.palworldkorea_online.post.announcement.entity.Announcement;
import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config= CommonPostMapper.class)
public interface AnnouncementMapper extends CommonPostMapper<Announcement, AnnouncementDto.Request, AnnouncementDto.Response> {
}
