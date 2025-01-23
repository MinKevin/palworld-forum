package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidPostIdException extends CustomException {
    public InvalidPostIdException() {
        super(ErrorCode.INVALID_POST_ID_EXCEPTION);
    }
}
