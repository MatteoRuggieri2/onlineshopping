package com.mr.onlineshopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ArticleQtaNotAvailable extends Exception {

    /* In questo modo quando lancio l'exception, con il super() richiama il costruttore
    della classe Exception, Exception(String message) */
    public ArticleQtaNotAvailable(int articleId, int requestedQta, int availableQta) {
        super("ArticleQtaNotAvailable: Item (id '"
                + articleId + "') not available in the requested quantity (requested: "
                + requestedQta + ", available: "
                + availableQta + ")");
    }
}
