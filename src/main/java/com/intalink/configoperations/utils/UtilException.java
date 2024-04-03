package com.intalink.configoperations.utils;

/**
 * 工具类异常
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;
    private Integer status;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UtilException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public UtilException(Integer status, String message, Throwable e) {
        super(message, e);
        this.status = status;
    }

}
