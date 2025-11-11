package com.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UpdatePasswordValidationException extends RuntimeException{
    public UpdatePasswordValidationException(String message) {
        super(message);
    }
}
