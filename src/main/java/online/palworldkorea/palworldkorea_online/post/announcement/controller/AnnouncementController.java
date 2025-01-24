package online.palworldkorea.palworldkorea_online.post.announcement.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.post.announcement.dto.AnnouncementDto;
import online.palworldkorea.palworldkorea_online.post.announcement.entity.Announcement;
import online.palworldkorea.palworldkorea_online.post.announcement.mapper.AnnouncementMapper;
import online.palworldkorea.palworldkorea_online.post.announcement.service.AnnouncementService;
import online.palworldkorea.palworldkorea_online.post.common.controller.CommonPostController;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import online.palworldkorea.palworldkorea_online.post.unified.mapper.UnifiedPostMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController extends CommonPostController<
        Announcement,
        AnnouncementDto.Request,
        AnnouncementDto.Response,
        AnnouncementMapper> {
    public AnnouncementController(AnnouncementService announcementService) {
        super(announcementService);
    }
}
