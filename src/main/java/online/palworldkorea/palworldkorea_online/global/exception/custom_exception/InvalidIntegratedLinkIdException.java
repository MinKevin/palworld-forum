package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidIntegratedLinkIdException extends CustomException {
    public InvalidIntegratedLinkIdException() {
        super(ErrorCode.INVALID_INTEGRATED_LINK_EXCEPTION);
    }
}
