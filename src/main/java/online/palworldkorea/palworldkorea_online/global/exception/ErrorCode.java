package online.palworldkorea.palworldkorea_online.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    ALREADY_EXISTS_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    ALREADY_EXISTS_NICKNAME_EXCEPTION(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    BINDING_EXCEPTION(HttpStatus.BAD_REQUEST, "Binding Exception"),
    EMAIL_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다."),
    INVALID_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다."),
    INVALID_REFRESH_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 Refresh Token 입니다."),
    INVALID_ACCESS_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 Access Token 입니다."),
    INVALID_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 Token 정보 입니다."),
    ;

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
