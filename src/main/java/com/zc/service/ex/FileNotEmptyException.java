package com.zc.service.ex;

public class FileNotEmptyException extends ServiceException {
    public FileNotEmptyException() {
    }

    public FileNotEmptyException(String message) {
        super(message);
    }

    public FileNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotEmptyException(Throwable cause) {
        super(cause);
    }

    public FileNotEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
