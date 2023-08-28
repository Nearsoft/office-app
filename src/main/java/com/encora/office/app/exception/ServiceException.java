package com.encora.office.app.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private HttpStatus httpStatus;
    private String messageDetails;
    private String errorCode;
    private String messageDescription;
}
