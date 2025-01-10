package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidRefreshTokenException extends CustomException {
    public InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN_EXCEPTION);
    }
}
