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

    @Column(name = "qta")
    private int qta;
}
