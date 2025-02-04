package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class InvalidAttachmentIdException extends CustomException {
    public InvalidAttachmentIdException() {
        super(ErrorCode.INVALID_ATTACHMENT_ID_EXCEPTION);
    }
}
