package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class CartNotFound extends Exception {
    private int cartId;

    public CartNotFound(String message) {
        super(message);
    }

    public CartNotFound(int cartId) {
        super("CartNotFound: The cart with id " + cartId + " was not found");
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "CartNotFound: The cart with id " + cartId + " was not found";
    }
}
