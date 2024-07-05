package com.mr.onlineshopping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartArticleID implements Serializable {

    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "article_id")
    private int articleId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartArticleID that = (CartArticleID) o;
        return cartId == that.cartId && articleId == that.articleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, articleId);
    }
}
