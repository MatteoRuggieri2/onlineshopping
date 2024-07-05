package com.mr.onlineshopping.dto;

public class OrderArticleDTO {
    private ArticleDTO article;
    private int qta;

    public OrderArticleDTO() {
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }
}
