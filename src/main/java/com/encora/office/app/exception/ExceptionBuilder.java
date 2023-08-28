package com.encora.office.app.exception;

import org.springframework.http.HttpStatus;

public class ExceptionBuilder {

    private final static String BASE_ERROR_MESSAGE = "The field '%s' should '%s'";
    private final static String VALIDATION_ERROR = "VE-01";
    private final static String VALIDATION_ERROR_DESC = String
            .format("%s - Request was rejected because of validation issues.", VALIDATION_ERROR);

    public static RuntimeException buildServiceValidationException(String field, String constraint) {
        return new ServiceException(HttpStatus.BAD_REQUEST, String.format(BASE_ERROR_MESSAGE, field, constraint),
                VALIDATION_ERROR, VALIDATION_ERROR_DESC);
    }

}
