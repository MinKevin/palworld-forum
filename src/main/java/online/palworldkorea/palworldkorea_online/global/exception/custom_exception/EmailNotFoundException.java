package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class EmailNotFoundException extends CustomException {
    public EmailNotFoundException() {
        super(ErrorCode.EMAIL_NOT_FOUND_EXCEPTION);
    }
}
