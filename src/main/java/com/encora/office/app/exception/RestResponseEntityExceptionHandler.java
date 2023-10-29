package com.encora.office.app.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.encora.office.app.constants.Constants.*;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<Object> handlerServiceException(
            ServiceException ex, WebRequest request, HttpServletRequest httpRequest) {

        log.debug("Catched ServiceException {} for request: {}", ex.toString(), request);

        Map<String, Object> exceptionMap = new HashMap<String, Object>();
        exceptionMap.put(REQUEST, httpRequest.getRequestURI());
        exceptionMap.put(HTTP_STATUS, ex.getHttpStatus());
        exceptionMap.put(MSG_DETAILS, ex.getMessageDetails());
        exceptionMap.put(ERROR_CODE, ex.getErrorCode());
        exceptionMap.put(MSG_DESCRIPTION, ex.getMessageDescription());

        return new ResponseEntity<Object>(exceptionMap, ex.getHttpStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleGeneralException(
            Exception ex, WebRequest request, HttpServletRequest httpRequest) {

        log.debug("Caught GeneralException {} for request: {}", ex.toString(), request);

        Map<String, Object> exceptionMap = new HashMap<String, Object>();
        exceptionMap.put(REQUEST, httpRequest.getRequestURI());
        exceptionMap.put(HTTP_STATUS, 500);
        exceptionMap.put(MSG_DETAILS, ex.toString());
        exceptionMap.put(ERROR_CODE, "ERR-500");
        exceptionMap.put(MSG_DESCRIPTION, ex.getMessage());

        return new ResponseEntity<Object>(exceptionMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}