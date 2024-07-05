package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.User;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CartDTO {
    private int id;
    private BigDecimal totalPrice;
    private User user; // Qui verrà salvato l'intero user
    private Set<Article> articles = new HashSet<>();

    public CartDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
