package online.palworldkorea.palworldkorea_online.post.attachment.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import org.springframework.web.multipart.MultipartFile;

public class AttachmentDto {
    private AttachmentDto() {}

    @Getter
    @Setter
    public static class Request {
        private MultipartFile attachment;
    }

    @Getter
    @Setter
    public static class Response {
        @ManyToOne(fetch = FetchType.EAGER)
        private String author;
        private String fileName;
        private String fileType;
        private long fileSize;
        private String filePath;
    }
}
