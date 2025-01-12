package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidMemberIdException extends CustomException {
    public InvalidMemberIdException() {
        super(ErrorCode.INVALID_MEMBER_ID_EXCEPTION);
    }
}
