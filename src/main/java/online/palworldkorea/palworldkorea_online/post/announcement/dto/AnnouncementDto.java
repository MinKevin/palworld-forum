package online.palworldkorea.palworldkorea_online.post.announcement.dto;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.announcement.entity.Announcement;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;

import java.util.List;

public class AnnouncementDto {
    private AnnouncementDto(){}

    public static class Request extends CommonPostDto.Request {
        @Override
        public Announcement toEntity(Member author, List<Attachment> attachments) {
            return new Announcement(author, super.getTitle(), super.getContent(), attachments);
        }
    }

    public static class Response extends CommonPostDto.Response {
    }
}
