package online.palworldkorea.palworldkorea_online.post.announcement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class AnnouncementDto {
    private AnnouncementDto(){}

    @Setter
    @Getter
    public static class Request {
        @NotNull
        private String title;

        @NotNull
        private String content;

        private List<String> attachments;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String title;
        private String content;
        private List<String> attachments;
    }
}
