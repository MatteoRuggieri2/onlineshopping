package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class CartNotFound extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message).
    In questo caso ho aggiunto un costruttore con un messaggio generico nel caso in cui
    l'utente non ha un carrello e quindi non c'è un cartId */
    public CartNotFound(String message) {
        super("CartNotFound: " + message);
    }

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public CartNotFound(int cartId) {
        super("CartNotFound: The cart with id " + cartId + " was not found");
    }
}
