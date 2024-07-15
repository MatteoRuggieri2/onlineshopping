package com.mr.onlineshopping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_article")
public class OrderArticle {

    // Id composto (order_id + article_id)
    @EmbeddedId
    private OrderArticleID orderArticleID;

    @Column(name = "article_qta")
    private int qta;

    //TODO: Per la relazione one to many con la tabella ponte
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    //TODO: Per la relazione one to many con la tabella ponte
    @ManyToOne
    @MapsId("articleId")
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
}