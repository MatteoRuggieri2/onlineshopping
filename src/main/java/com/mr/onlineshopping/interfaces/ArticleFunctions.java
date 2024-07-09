package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.exceptions.ArticleNotFound;

import java.util.List;
import java.util.Optional;

public interface ArticleFunctions {
    List<Article> getAllArticle();
    Optional<Article> getArticleById(int articleId);
    boolean checkAvailability(int articleId, int qta) throws ArticleNotFound;
    boolean addArticleQta(int articleId, int qta);
    boolean removeArticleQta(int articleId, int qta);
}
