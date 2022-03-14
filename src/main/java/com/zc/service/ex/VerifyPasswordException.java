package com.zc.service.ex;

public class VerifyPasswordException extends ServiceException {
    public VerifyPasswordException() {
    }

    public VerifyPasswordException(String message) {
        super(message);
    }

    public VerifyPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyPasswordException(Throwable cause) {
        super(cause);
    }

    public VerifyPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
