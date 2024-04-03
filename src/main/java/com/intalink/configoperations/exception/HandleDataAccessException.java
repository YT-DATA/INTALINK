package com.intalink.configoperations.exception;

import com.intalink.configoperations.utils.UtilException;
import org.springframework.dao.DataAccessException;

public class HandleDataAccessException {
    public HandleDataAccessException() {
    }

    public static UtilException getDrxdException(DataAccessException e, String headerMessage) throws Exception {
        e.printStackTrace();
        if (e.getCause() != null) {
            String[] split = e.getCause().toString().split(":");
            String message = "";
            if (split.length >= 3) {
                message = headerMessage + "--" + split[1].trim() + ":" + split[2].trim();
            } else {
                message = headerMessage + "--" + split[1].trim();
            }

            return new UtilException(100, message, e);
        } else {
            return new UtilException(100, "未知异常");
        }
    }
}

