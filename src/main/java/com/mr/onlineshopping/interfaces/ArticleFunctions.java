package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.Article;

import java.util.List;

public interface ArticleFunctions {
    List<Article> getAllArticle();
    Article getArticleById(int articleId);
    boolean checkAvailability(int articleId, int qta);
    boolean addArticleQta(int articleId, int qta);
    boolean removeArticleQta(int articleId, int qta);
}
