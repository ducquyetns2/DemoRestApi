package com.ducquyet.restapi.exception;

import com.ducquyet.restapi.entity.ErrorsDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorsDetail> customHandleException(Exception ex, WebRequest request) {
        return new ResponseEntity<ErrorsDetail>(
                new ErrorsDetail(LocalDateTime.now(),ex.getMessage())
                ,HttpStatus.NOT_FOUND);
    }
}
