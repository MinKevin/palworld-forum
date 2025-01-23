package online.palworldkorea.palworldkorea_online.post.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.comment.entity.Comment;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;

import java.util.List;

public class CommentDto {
    private CommentDto(){}

    @Getter
    @Setter
    public static class Request {
        @NotBlank
        private String content;

        public Comment toEntity(Member author, CommonPost post, Comment parent) {
            return new Comment(author, post, content, parent);
        }
    }

    @Getter
    @Setter
    public static class Response {
        private long id;
        private String author;
        private String content;
        private List<Response> childComments;
    }
}
