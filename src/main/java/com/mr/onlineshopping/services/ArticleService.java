package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.interfaces.ArticleFunctions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements ArticleFunctions {

    @Override
    public List<Article> getAllArticle() {
        return List.of();
    }

    @Override
    public Article getArticleById(int articleId) {
        return null;
    }

    @Override
    public boolean checkAvailability(int articleId, int qta) {
        return false;
    }

    @Override
    public boolean addArticleQta(int articleId, int qta) {
        return false;
    }

    @Override
    public boolean removeArticleQta(int articleId, int qta) {
        return false;
    }
}
