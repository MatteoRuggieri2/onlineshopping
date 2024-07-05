package com.mr.onlineshopping.dto;

public class CartArticleDTO {
    private ArticleDTO article;
    private int qta;

    public CartArticleDTO() {
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }
}
