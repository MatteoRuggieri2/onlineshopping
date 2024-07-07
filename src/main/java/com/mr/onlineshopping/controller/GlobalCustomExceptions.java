package com.mr.onlineshopping.controller;

import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// Questo controller serve per avere tutte le gestioni degli errori a livello globale.
// Questi metodi vengono richiamati quando il controller intercetta in questo caso un'eccezione qualsiasi per il primo metodo
// e un'eccezione di tipo CartNotFound per il secondo metodo (dobbiamo fare noi il throw new MiaEccezione)
@ControllerAdvice
public class GlobalCustomExceptions extends ResponseEntityExceptionHandler {

    //override method of ResponseEntityExceptionHandler class
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        //creating exception response structure
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());

        //returning exception structure and specific status
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CartNotFound.class)
    public final ResponseEntity<Object> quantityNotAvailableExceptions(CartNotFound ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
