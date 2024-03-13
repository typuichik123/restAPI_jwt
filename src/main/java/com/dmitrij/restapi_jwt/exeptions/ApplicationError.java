package com.dmitrij.restapi_jwt.exeptions;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationError {
    private int status;
    private String message;
    private Date timeStamp;

    public ApplicationError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = new Date();
    }
}

