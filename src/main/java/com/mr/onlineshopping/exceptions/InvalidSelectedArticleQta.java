package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class InvalidSelectedArticleQta extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public InvalidSelectedArticleQta(int articleId, int articleQta) {
        super("InvalidSelectedArticleQta: The selected quantity (articleQta = "
                + articleQta + ") of the article (articleId = "
                + articleId + ") is invalid");
    }
}
