package com.mr.onlineshopping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_article")
public class CartArticle {

    // Id composto (cart_id + article_id)
    @EmbeddedId
    private CartArticleID cartArticleID;

    @Column(name = "article_qta")
    private int qta;

    //TODO: Per la relazione one to many con la tabella ponte
    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    //TODO: Per la relazione one to many con la tabella ponte
    @ManyToOne
    @MapsId("articleId")
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

}
