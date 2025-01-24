package online.palworldkorea.palworldkorea_online.post.announcement.service;

import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.announcement.dto.AnnouncementDto;
import online.palworldkorea.palworldkorea_online.post.announcement.entity.Announcement;
import online.palworldkorea.palworldkorea_online.post.announcement.mapper.AnnouncementMapper;
import online.palworldkorea.palworldkorea_online.post.announcement.repository.AnnouncementRepository;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService extends CommonPostService<
        Announcement,
        AnnouncementDto.Request,
        AnnouncementDto.Response,
        AnnouncementMapper> {

    public AnnouncementService(MemberService memberService,
                               AttachmentService attachmentService,
                               AnnouncementRepository announcementRepository,
                               AnnouncementMapper announcementMapper) {
        super(memberService, attachmentService, announcementRepository, announcementMapper, Announcement.class);
    }
}
