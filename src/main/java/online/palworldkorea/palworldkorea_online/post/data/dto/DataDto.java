package online.palworldkorea.palworldkorea_online.post.data.dto;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.data.entity.Data;

import java.util.List;

public class DataDto {
    private DataDto() {}

    public static class Request extends CommonPostDto.Request {
        @Override
        public Data toEntity(Member author, List<Attachment> attachments) {
            return new Data(author, super.getTitle(), super.getContent(), attachments);
        }
    }

    public static class Response extends CommonPostDto.Response {}
}
