package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidCommentIdException extends CustomException {
    public InvalidCommentIdException() {
        super(ErrorCode.INVALID_COMMENT_ID_EXCEPTION);
    }
}
