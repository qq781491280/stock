package com.zc.service.ex;

public class VerifyUsernameException extends ServiceException {
    public VerifyUsernameException() {
    }

    public VerifyUsernameException(String message) {
        super(message);
    }

    public VerifyUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyUsernameException(Throwable cause) {
        super(cause);
    }

    public VerifyUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

