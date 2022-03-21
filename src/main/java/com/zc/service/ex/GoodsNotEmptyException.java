package com.zc.service.ex;

public class GoodsNotEmptyException extends ServiceException {
    public GoodsNotEmptyException() {
    }

    public GoodsNotEmptyException(String message) {
        super(message);
    }

    public GoodsNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodsNotEmptyException(Throwable cause) {
        super(cause);
    }

    public GoodsNotEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
