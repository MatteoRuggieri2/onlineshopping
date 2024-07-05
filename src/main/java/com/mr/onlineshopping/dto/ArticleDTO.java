package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.Cart;

import java.math.BigDecimal;
import java.util.Set;

public class ArticleDTO {
    private int id;
    private String articleCode;
    private String name;
    private String description;
    private String thumb;
    private BigDecimal price;
    private int availableQta;
    private Set<Cart> carts;
    private Set<Cart> orders;

    public ArticleDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAvailableQta() {
        return availableQta;
    }

    public void setAvailableQta(int availableQta) {
        this.availableQta = availableQta;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<Cart> getOrders() {
        return orders;
    }

    public void setOrders(Set<Cart> orders) {
        this.orders = orders;
    }
}
