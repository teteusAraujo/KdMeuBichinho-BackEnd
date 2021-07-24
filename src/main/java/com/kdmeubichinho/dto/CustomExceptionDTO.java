package com.kdmeubichinho.dto;

/**
 * Created by root on 05/12/16.
 */
public class CustomExceptionDTO {

    private String message;

    public CustomExceptionDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
