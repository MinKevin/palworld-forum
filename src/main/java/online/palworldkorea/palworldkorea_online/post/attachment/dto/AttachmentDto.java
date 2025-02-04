package online.palworldkorea.palworldkorea_online.post.attachment.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

public class AttachmentDto {
    private AttachmentDto() {}

    @Getter
    @Setter
    public static class Request {
        private String filePath;
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
