package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.Cart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {

    private int id;
    private String articleCode;
    private String name;
    private String description;
    private String thumb;
    private BigDecimal price;
    private int availableQta;
}
