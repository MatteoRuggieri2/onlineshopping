package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ToFewItemInTheCart extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public ToFewItemInTheCart(int cartId, int articleId, int articleQta, int availableQta) {
        super("ToFewItemInTheCart: You are trying to delete a larger quantity ("
                + articleQta + " pieces) of the item (id '"
                + articleId + "') than what you have available in your cart (id '"
                + cartId + "') ("
                + availableQta + " pieces available)");
    }
}
