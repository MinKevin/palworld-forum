package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class AccessDeniedException extends CustomException {
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED_EXCEPTION);
    }
}
