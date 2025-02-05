package online.palworldkorea.palworldkorea_online.post.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.post.attachment.dto.AttachmentDto;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.comment.dto.CommentDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class CommonPostDto {
    private CommonPostDto(){}

    @Getter
    @Setter
    public static class Request {
        @NotBlank
        private String title;

        @NotBlank
        private String content;

        private List<MultipartFile> attachments;


        public  CommonPost toEntity(Member author) {
            return new CommonPost(author, this.title, this.content);
        }
    }

    @Getter
    @Setter
    public static class Response {
        private long id;
        private String dtype;
        private String author;
        private String nickname;
        private MemberRole memberRole;
        private String title;
        private String content;
        private List<AttachmentDto.Response> attachments;
        private int hits;
        private long countOfComments;
        private List<CommentDto.Response> comments;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
