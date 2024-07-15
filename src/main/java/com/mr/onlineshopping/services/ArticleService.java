package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.exceptions.ArticleNotAvailable;
import com.mr.onlineshopping.exceptions.ArticleNotFound;
import com.mr.onlineshopping.interfaces.ArticleFunctions;
import com.mr.onlineshopping.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements ArticleFunctions {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public boolean existsById(int articleId) {
        return articleRepository.existsById(articleId);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
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
    public boolean addArticleQta(int articleId, int qta) throws ArticleNotFound {

        /* Controllo se esiste l'articolo, poi prendo la qta dell'articolo nel magazzino
        e aggiungo a qtaAvailable + qta */
        Article article = this.getArticleById(articleId).orElseThrow(() -> new ArticleNotFound(articleId));
        article.setAvailableQta(article.getAvailableQta() + qta);
        articleRepository.save(article);
        return true;
    }
    @Transactional
    @Override
    public boolean removeArticleQta(int articleId, int qta) throws ArticleNotFound, ArticleNotAvailable {

        /* Controllo se esiste l'articolo, controllo che la qta richiesta sia inferiore
        o uguale a qtaAvailable e poi rimuovo gli articoli (qtaAvailable - qta) */
        Article article = this.getArticleById(articleId).orElseThrow(() -> new ArticleNotFound(articleId));
        if (!this.checkAvailability(articleId, qta)) { throw new ArticleNotAvailable(articleId, qta); }
        article.setAvailableQta(article.getAvailableQta() - qta);
        articleRepository.saveAndFlush(article);
        return true;
    }
}
