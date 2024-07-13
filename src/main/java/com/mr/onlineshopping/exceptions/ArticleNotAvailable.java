package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ArticleNotAvailable extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public ArticleNotAvailable(int articleId, int articleQta) {
        super("ArticleNotAvailable: The article (articleId: "
                + articleId + ") is not available in the requested quantity ("
                + articleQta + " pieces)");
    }
}
