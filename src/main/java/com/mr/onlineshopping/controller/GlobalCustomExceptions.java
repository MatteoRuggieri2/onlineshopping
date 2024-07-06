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
// e un'eccezione di tipo QuantityNotAvailable per il secondo metodo (dobbiamo fare noi il throw new MiaEccezione)
//@ControllerAdvice
//public class GlobalCustomExceptions extends ResponseEntityExceptionHandler {
//
//    // override method of ResponseEntityExceptionHandler class
//    @ExceptionHandler(CartNotFound.class)
//    public final ResponseEntity<Object> quantityNotAvailableExceptions(CartNotFound ex, WebRequest request) {
//        // creating exception response structure
//        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        // returning exception structure and Not Found status
//        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
//    }
//}
