package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class NotEnoughPermissionException extends CustomException {
    public NotEnoughPermissionException() {
        super(ErrorCode.NOT_ENOUGH_PERMISSION_EXCEPTION);
    }
}
