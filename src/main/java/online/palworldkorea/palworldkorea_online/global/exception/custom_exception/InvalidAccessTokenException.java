package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidAccessTokenException extends CustomException {
    public InvalidAccessTokenException() {
        super(ErrorCode.INVALID_ACCESS_TOKEN_EXCEPTION);
    }
}
