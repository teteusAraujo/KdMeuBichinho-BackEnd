package com.kdmeubichinho.exception;

import com.kdmeubichinho.enums.EnumException;
import org.springframework.http.HttpStatus;

/**
 * Validation exceptions. Exceptions related to server-side verifications (i.e. user not found (404 - NOT_FOUND), etc).
 */
public class ValidationException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5957756808614122964L;
	
	private final HttpStatus httpStatus;
    private final String description;

    public ValidationException(EnumException exceptionEnum) {
        this.description = exceptionEnum.getDescription();
        this.httpStatus = exceptionEnum.getHttpStatus();
    }

    public ValidationException(String description, HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDescription() {
        return description;
    }
}
