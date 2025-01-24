package online.palworldkorea.palworldkorea_online.post.guide.dto;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.guide.entity.Guide;

import java.util.List;

public class GuideDto {
    private GuideDto(){}

    public static class Request extends CommonPostDto.Request {
        @Override
        public Guide toEntity(Member author, List<Attachment> attachments) {
            return new Guide(author, super.getTitle(), super.getContent(), attachments);
        }
    }

    public static class Response extends CommonPostDto.Response {}
}
