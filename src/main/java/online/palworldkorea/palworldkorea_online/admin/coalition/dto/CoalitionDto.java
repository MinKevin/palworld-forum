package online.palworldkorea.palworldkorea_online.admin.coalition.dto;

import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.post.attachment.dto.AttachmentDto;
import org.springframework.web.multipart.MultipartFile;

public class CoalitionDto {
    private CoalitionDto() {}

    @Getter
    @Setter
    public static class Request {
        private Long id;
        private String name;
        private MultipartFile attachment;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String name;
        private AttachmentDto.Response attachment;
    }
}
