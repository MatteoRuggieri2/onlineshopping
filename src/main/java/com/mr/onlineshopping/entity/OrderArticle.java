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

    @Column(name = "qta")
    private int qta;
}