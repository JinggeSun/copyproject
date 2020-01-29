package com.sun.jwt.bean;

import java.io.Serializable;

/**
 * @author zcm
 */
public class HttpResponseBean implements Serializable {

    private String httpStatus;
    private String message;

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
