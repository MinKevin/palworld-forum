package online.palworldkorea.palworldkorea_online.admin.link.dto;

import lombok.Getter;
import lombok.Setter;
import online.palworldkorea.palworldkorea_online.admin.LinkType;

public class LinkDto {
    private LinkDto() {}

    @Getter
    @Setter
    public static class Request {
        private LinkType linkType;
        private String url;
    }

    @Getter
    @Setter
    public static class Response {
        private LinkType linkType;
        private String url;
    }
}
