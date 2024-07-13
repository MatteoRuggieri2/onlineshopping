package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.CartArticle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {

    private int id;
    private BigDecimal totalPrice;
    private UserDTO user; // Così verrà salvato l'intero user
    //private int userId; // Qui verrà salvato il suo id
//    private List<ArticleDTO> articles;

//    TODO: Per la relazione one to many con la tabella ponte
    private List<CartArticleDTO> cartArticles;

}
