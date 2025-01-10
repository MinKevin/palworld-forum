package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class AlreadyExistsEmailException extends CustomException {
    public AlreadyExistsEmailException() {
        super(ErrorCode.ALREADY_EXISTS_EMAIL_EXCEPTION);
    }
}
