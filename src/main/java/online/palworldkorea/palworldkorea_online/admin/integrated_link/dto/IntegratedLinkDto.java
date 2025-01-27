package online.palworldkorea.palworldkorea_online.admin.integrated_link.dto;

import lombok.Getter;
import lombok.Setter;

public class IntegratedLinkDto {
    private IntegratedLinkDto() {};

    @Getter
    @Setter
    public static class Request {
        private Long id;
        private String title;
        private String url;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String title;
        private String url;
    }
}
