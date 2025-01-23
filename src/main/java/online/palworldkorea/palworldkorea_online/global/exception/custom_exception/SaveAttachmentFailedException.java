package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class SaveAttachmentFailedException extends CustomException {
    public SaveAttachmentFailedException() {
        super(ErrorCode.SAVE_ATTACHMENT_FAILED_EXCEPTION);
    }
}
