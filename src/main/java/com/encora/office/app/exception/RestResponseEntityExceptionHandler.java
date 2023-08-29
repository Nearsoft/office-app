package com.encora.office.app.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private String REQUEST = "request";
    private String HTTP_STTATUS = "httpStatus";
    private String MSG_DETAILS = "messageDetails";
    private String ERROR_CODE = "errorCode";
    private String MSG_DESCRIPTION = "messageDescription";

    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            ServiceException ex, WebRequest request, HttpServletRequest httpRequest) {

        log.debug("Catched ServiceException {} for request: {}", ex.toString(), request);

        Map<String, Object> exceptionMap = new HashMap<String, Object>();
        exceptionMap.put(REQUEST, httpRequest.getRequestURI());
        exceptionMap.put(HTTP_STTATUS, ex.getHttpStatus());
        exceptionMap.put(MSG_DETAILS, ex.getMessageDetails());
        exceptionMap.put(ERROR_CODE, ex.getErrorCode());
        exceptionMap.put(MSG_DESCRIPTION, ex.getMessageDescription());

        return new ResponseEntity<Object>(exceptionMap, ex.getHttpStatus());
    }

}