package online.palworldkorea.palworldkorea_online.email.dto;

import lombok.Getter;
import lombok.Setter;

public class EmailVerificationDto {
    private EmailVerificationDto(){}

    @Getter
    @Setter
    public static class Request {
        private String email;
        private String verificationCode;
    }
}
