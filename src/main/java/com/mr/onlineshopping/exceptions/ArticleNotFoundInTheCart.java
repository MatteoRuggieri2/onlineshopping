package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ArticleNotFoundInTheCart extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public ArticleNotFoundInTheCart(int articleId, int cartId) {
        super("ArticleNotFoundInTheCart: The item with id "
                + articleId + " is not present in the cart with id "
                + cartId);
    }
}
