package online.palworldkorea.palworldkorea_online.post.free.dto;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.free.entity.Free;

import java.util.List;

public class FreeDto {
    private FreeDto() {}

    public static class Request extends CommonPostDto.Request {
        @Override
        public Free toEntity(Member author, List<Attachment> attachments) {
            return new Free(author, super.getTitle(), super.getContent(), attachments);
        }
    }

    public static class Response extends CommonPostDto.Response {}
}
