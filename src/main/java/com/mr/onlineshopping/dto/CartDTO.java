package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {

    private int id;
    private BigDecimal totalPrice;
    private User user; // Qui verrà salvato l'intero user
    private Set<Article> articles = new HashSet<>();
}
