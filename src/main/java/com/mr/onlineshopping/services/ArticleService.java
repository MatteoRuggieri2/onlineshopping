package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.exceptions.ArticleNotFound;
import com.mr.onlineshopping.interfaces.ArticleFunctions;
import com.mr.onlineshopping.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements ArticleFunctions {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticle() {
        return List.of();
    }

    @Override
    public Optional<Article> getArticleById(int articleId) {
        return articleRepository.findById(articleId);
    }

    /* Questo metodo ritorna TRUE se l'articolo è disponibile nella
    qta richiesta, altrimenti ritorna FALSE */
    @Override
    public boolean checkAvailability(int articleId, int qta) throws ArticleNotFound {
        Article article = this.getArticleById(articleId).orElseThrow(() -> new ArticleNotFound(articleId));
        return article.getAvailableQta() >= qta;
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
