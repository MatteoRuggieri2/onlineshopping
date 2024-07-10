package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class CartAlreadyExists extends Exception {

    public CartAlreadyExists(String message) {
        super("CartAlreadyExists: " + message);
    }

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public CartAlreadyExists(int cartId) {
        super("CartAlreadyExists: The cart with id '"
                + cartId + "' already exists.");
    }
}
