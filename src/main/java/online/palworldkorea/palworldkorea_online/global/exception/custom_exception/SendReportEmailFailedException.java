package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class SendReportEmailFailedException extends CustomException {
    public SendReportEmailFailedException() {
        super(ErrorCode.SEND_REPORT_EMAIL_FAILED_EXCEPTION);
    }
}
