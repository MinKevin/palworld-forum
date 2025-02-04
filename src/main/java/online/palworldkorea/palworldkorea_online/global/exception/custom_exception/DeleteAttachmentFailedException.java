package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class DeleteAttachmentFailedException extends CustomException {
    public DeleteAttachmentFailedException() {
        super(ErrorCode.DELETE_ATTACHMENT_FAILED_EXCEPTION);
    }
}
