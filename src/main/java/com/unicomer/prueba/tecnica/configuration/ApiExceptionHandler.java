package com.unicomer.prueba.tecnica.configuration;


import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import com.unicomer.prueba.tecnica.shared.dto.ErrorMessageResponse;
import com.unicomer.prueba.tecnica.shared.exception.ControllerException;
import com.unicomer.prueba.tecnica.shared.exception.EntityNotFoundException;
import com.unicomer.prueba.tecnica.shared.exception.ServiceException;

@RestControllerAdvice
@CommonsLog
public class ApiExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorMessageResponse> handleUnknownHostException(ServiceException ex) {
        
        ErrorMessageResponse response = ErrorMessageResponse.builder()
                .code("E-670")
                .detail("call to Admin")
                .title("Error")
                .type("Internal Error")
                .instance("salesman-api")
                .build();
        ;
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(ControllerException e) {

        ErrorMessageResponse response = ErrorMessageResponse.builder()
                .code("E-671")
                .detail("call to Admin")
                .title("Error")
                .type("Internal")
                .instance("salesman-api")
                .build();
        return new ResponseEntity<ErrorMessageResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleEntityNoFoundException(EntityNotFoundException ex) {

        ErrorMessageResponse response = ErrorMessageResponse.builder()
                .code("404")
                .detail(ex.getMessage())
                .title("Not Found")
                .type("NotFound")
                .instance("salesman-api")
                .build();
        ;
        return new ResponseEntity<ErrorMessageResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        StringBuilder strb = new StringBuilder();
        errors.forEach(er -> strb.append(er.getField()).append(" : ").append(er.getDefaultMessage()+" "));
        ErrorMessageResponse response = ErrorMessageResponse.builder()
                .code("400")
                .detail(strb.toString())
                .title("Error")
                .type("Validation Error")
                .instance("salesman-api")
                .build();
        ;
        return new ResponseEntity<ErrorMessageResponse>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(BindException ex) {
        log.error(ex.getMessage(), ex);

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        StringBuilder strb = new StringBuilder();
        errors.forEach(er -> strb.append(er.getField()).append(" : ").append(er.getDefaultMessage()+" "));
        ErrorMessageResponse response = ErrorMessageResponse.builder()
                .code("400")
                .detail(strb.toString())
                .title("Error")
                .type("Validation Error")
                .instance("salesman-api")
                .build();
        ;
        return new ResponseEntity<ErrorMessageResponse>(response, HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> handleException(Exception ex) {
        ErrorMessageResponse response = ErrorMessageResponse.builder()
                .code("E-672")
                .detail("call to Admin")
                .title("Error")
                .type("Uncategorized")
                .instance("salesman-api")
                .build();
        ;
        return new ResponseEntity<ErrorMessageResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
