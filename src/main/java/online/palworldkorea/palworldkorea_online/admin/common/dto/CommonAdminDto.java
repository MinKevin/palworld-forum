package online.palworldkorea.palworldkorea_online.admin.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.post.attachment.dto.AttachmentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CommonAdminDto {
    private CommonAdminDto(){}

    @Getter
    @Setter
    public static class Request {
        @NotBlank
        private String content;

        private List<MultipartFile> attachments;
    }

    @Getter
    @Setter
    public static class Response {
        private String content;

        private List<AttachmentDto.Response> attachments;
    }
}
