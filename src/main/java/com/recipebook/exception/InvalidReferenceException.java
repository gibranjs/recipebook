package com.recipebook.exception;

import org.springframework.validation.FieldError;

import javax.validation.ValidationException;

@SuppressWarnings("unused")
public class InvalidReferenceException extends ValidationException {

    private FieldError fieldError;

    public InvalidReferenceException(FieldError fieldError) {
        this.fieldError = fieldError;
    }

    public InvalidReferenceException(String objectName, String field, String defaultMessage) {
        this.fieldError = new FieldError(objectName, field, defaultMessage);
    }

    public InvalidReferenceException(String objectName, String field, Object rejectedValue, String defaultMessage) {
        this.fieldError = new FieldError(objectName, field, rejectedValue, false, null, null, defaultMessage);
    }

    public FieldError getInvalidReference() {
        return fieldError;
    }
}
