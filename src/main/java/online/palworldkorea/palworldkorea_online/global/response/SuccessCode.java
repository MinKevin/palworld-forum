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
    GENERATE_VERIFICATION_CODE_SUCCESS(HttpStatus.CREATED, "이메일로 인증 번호를 발송하였습니다."),
    VERIFY_VERTIFICATION_CODE_SUCCESS(HttpStatus.ACCEPTED, "이메일 인증이 완료되었습니다."),
    SEND_REPORT_TO_ADMIN_SUCCESS(HttpStatus.ACCEPTED, "신고 메일을 관리자에게 발송했습니다.");

    private final HttpStatus httpStatus;
    private final String reasonPhrase;

    SuccessCode(HttpStatus httpStatus, String reasonPhrase) {
        this.httpStatus = httpStatus;
        this.reasonPhrase = reasonPhrase;
    }
}
