package com.zc.service.ex;

public class GoodsException extends ServiceException {
    public GoodsException() {
    }

    public GoodsException(String message) {
        super(message);
    }

    public GoodsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodsException(Throwable cause) {
        super(cause);
    }

    public GoodsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
