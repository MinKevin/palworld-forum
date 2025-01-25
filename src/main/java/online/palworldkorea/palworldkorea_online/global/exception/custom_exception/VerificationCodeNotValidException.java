package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class VerificationCodeNotValidException extends CustomException {
    public VerificationCodeNotValidException() {
        super(ErrorCode.VERIFICATION_CODE_NOT_VALID_EXCEPTION);
    }
}
