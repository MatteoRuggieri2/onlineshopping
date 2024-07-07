package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class CartNotFound extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public CartNotFound(int cartId) {
        super("CartNotFound: The cart with id " + cartId + " was not found");
    }
}
