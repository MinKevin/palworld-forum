package online.palworldkorea.palworldkorea_online.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode {
    REGISTER_MEMBER_SUCCESS(HttpStatus.CREATED, "회원 가입에 성공했습니다."),
    DELETE_MEMBER_SUCCESS(HttpStatus.ACCEPTED, "회원 탈퇴가 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    REFRESH_ACCESS_TOKEN_SUCCESS(HttpStatus.CREATED, "ACCESS TOKEN 재발급에 성공했습니다."),
    GET_POST_SUCCESS(HttpStatus.OK, "게시글 조회에 성공했습니다."),
    POST_POST_SUCCESS(HttpStatus.CREATED, "게시글 등록에 성공했습니다."),
    UPDATE_POST_SUCCESS(HttpStatus.ACCEPTED, "게시글 수정에 성공했습니다."),
    DELETE_POST_SUCCESS(HttpStatus.ACCEPTED, "게시글 삭제에 성공했습니다."),
    GET_POST_LIST_SUCCESS(HttpStatus.OK, "게시판 조회를 완료했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String reasonPhrase;

    SuccessCode(HttpStatus httpStatus, String reasonPhrase) {
        this.httpStatus = httpStatus;
        this.reasonPhrase = reasonPhrase;
    }
}
