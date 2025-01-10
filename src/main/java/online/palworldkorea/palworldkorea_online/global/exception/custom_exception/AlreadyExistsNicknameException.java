package online.palworldkorea.palworldkorea_online.global.exception.custom_exception;

import online.palworldkorea.palworldkorea_online.global.exception.CustomException;
import online.palworldkorea.palworldkorea_online.global.exception.ErrorCode;

public class AlreadyExistsNicknameException extends CustomException {
    public AlreadyExistsNicknameException() {
        super(ErrorCode.ALREADY_EXISTS_NICKNAME_EXCEPTION);
    }
}
