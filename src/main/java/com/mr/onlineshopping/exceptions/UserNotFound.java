package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class UserNotFound extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public UserNotFound(int userId) {
        super("UserNotFound: The user with (id: " + userId + ") was not found");
    }

    public UserNotFound(String email) {
        super("UserNotFound: The user with email '" + email + "' was not found");
    }
}
