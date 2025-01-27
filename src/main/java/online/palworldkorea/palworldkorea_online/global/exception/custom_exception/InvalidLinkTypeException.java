package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidLinkTypeException extends CustomException {
    public InvalidLinkTypeException() {
        super(ErrorCode.INVALID_LINK_TYPE_EXCEPTION);
    }
}
