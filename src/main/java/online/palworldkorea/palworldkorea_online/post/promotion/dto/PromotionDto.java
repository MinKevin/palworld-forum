package online.palworldkorea.palworldkorea_online.post.promotion.dto;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.promotion.entity.Promotion;

import java.util.List;

public class PromotionDto {
    private PromotionDto() {}

    public static class Request extends CommonPostDto.Request {
        @Override
        public Promotion toEntity(Member author, List<Attachment> attachments) {
            return new Promotion(author, super.getTitle(), super.getContent(), attachments);
        }
    }

    public static class Response extends CommonPostDto.Response {}
}
