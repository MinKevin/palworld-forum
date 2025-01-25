package online.palworldkorea.palworldkorea_online.report.dto;

import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.post.attachment.dto.AttachmentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReportDto {
    private ReportDto() {}

    @Getter
    @Setter
    public static class Request {
        private String title;
        private String content;
        private List<MultipartFile> attachments;
    }
}
