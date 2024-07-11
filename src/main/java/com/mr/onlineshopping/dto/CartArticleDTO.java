package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.CartArticle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartArticleDTO {

    private ArticleDTO article;
    private int qta;

}
