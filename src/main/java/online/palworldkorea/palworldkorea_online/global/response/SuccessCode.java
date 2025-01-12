package online.palworldkorea.palworldkorea_online.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode {
    REGISTER_MEMBER_SUCCESS(HttpStatus.CREATED, "회원 가입에 성공했습니다."),
    DELETE_MEMBER_SUCCESS(HttpStatus.ACCEPTED, "회원 탈퇴가 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    REFRESH_ACCESS_TOKEN_SUCCESS(HttpStatus.CREATED, "ACCESS TOKEN 재발급에 성공했습니다."),
    GET_BOARD_SUCCESS(HttpStatus.OK, "게시판 조회에 성공했습니다."),
    POST_BOARD_SUCCESS(HttpStatus.CREATED, "게시판 등록에 성공했습니다."),
    UPDATE_BOARD_SUCCESS(HttpStatus.ACCEPTED, "게시판 수정에 성공했습니다."),
    DELETE_BOARD_SUCCESS(HttpStatus.ACCEPTED, "게시판 삭제에 성공했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String reasonPhrase;

    SuccessCode(HttpStatus httpStatus, String reasonPhrase) {
        this.httpStatus = httpStatus;
        this.reasonPhrase = reasonPhrase;
    }
}
